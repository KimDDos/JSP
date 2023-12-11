console.log("Board_detail.js In~!!!");

document.getElementById('cmtAddBtn').addEventListener('click', () => {
  const cmtText = document.getElementById('cmtText').value;
  if (cmtText == null || cmtText == '') {
    alert("댓글을 입력해주세요.");
    return false;
  } else {
    // 댓글 등록
    let cmtData = {
      bno: bnoVal,
      writer: document.getElementById('cmtWriter').value,
      content: cmtText
    };
    console.log(cmtData);

    // 댓글 등록시 비동기 통신 호출
    postCommentToServer(cmtData).then(result => {
      console.log(result);
      if (result > 0) {
        alert("댓글등록 성공 ~ !!");
        document.getElementById('cmtText').value = '';
      }
      // 댓글 출력
      printCommentList(bnoVal);
    })
  }
})


async function postCommentToServer(cmtData) {
  try {
    const url = "/cmt/post"
    const config = {
      method: 'post',
      Headers: {
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(cmtData)
    };

    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

async function getCommentListFromServer(bno) {
  try {
    const resp = await fetch("/cmt/list/" + bno);
    const result = await resp.json();
    return result;
  } catch (error) {
    console.log(error);
  }
}

function printCommentList(bno) {
  getCommentListFromServer(bno).then(result => {  // cmtList
    console.log(result);
    if (result.length > 0) {
      spreadCommentList(result);
    } else {
      let div = document.getElementById('commentLine');
      div.innerHTML = `<div> Comment가 없습니다. </div>`;
    }
  })
}

function spreadCommentList(result) {
  console.log("comment List >> " + result);
  let div = document.getElementById('commentLine');
  let writer = document.getElementById('cmtWriter').value;
  div.innerHTML = '';  // 원래 만들었던 구조 지우기
  for (let i = 0; i < result.length; i++) {
    let html = `<div>`;
    html += `<div>${result[i].cno}, ${result[i].bno}, ${result[i].writer}, ${result[i].regdate}</div>`;
    html += `<div>`;
    html += `<input type="text" class="cmtText" value="${result[i].content}">`;
    if (writer == result[i].writer) {
      html += `<button type="button" data-cno="${result[i].cno}" class="cmtModBtn">수정</button>`;
      html += `<button type="button" data-cno="${result[i].cno}" class="cmtDelBtn">삭제</button>`;
    }
    html += `</div></div>`;
    html += `<hr>`;
    div.innerHTML += html;  // 각 댓글 객체를 누적해서 담기
  }
}

document.addEventListener('click', (e) => {
  console.log(e.target);
  if (e.target.classList.contains('cmtDelBtn')) {
    let cnoVal = e.target.dataset.cno;
    console.log(cnoVal);
    removeCommentFromServer(cnoVal).then(result => {
      if (result > 0) {
        alert("댓글 삭제 완료");
        printCommentList(bnoVal);
      }
    })
  }

  if (e.target.classList.contains('cmtModBtn')) {
    let cnoVal = e.target.dataset.cno;
    console.log(cnoVal);
    let div = e.target.closest('div');
    console.log(div);
    let cmtText = div.querySelector('.cmtText').value;
    console.log(cmtText);
    updateCommentFromServer(cnoVal, cmtText).then(result => {
      if (result > 0) {
        alert('댓글 수정 성공');
        printCommentList(bnoVal);
      }
    })
  }

})

// 삭제 : cno => result isOk
async function removeCommentFromServer(cnoVal) {
  try {
    const url = "/cmt/remove?cnoVal=" + cnoVal;
    const resp = await fetch(url);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

// 수정 : cno, content 
async function updateCommentFromServer(cnoVal, cmtText) {
  try {
    const url = "/cmt/modify";
    const config = {
      method: 'post',
      Headers: {
        'Content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify({ cno: cnoVal, content: cmtText })
    };

    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}