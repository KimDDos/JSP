console.log("board_detail.js in ~!!!");
console.log(bnoVal);

// 컨트롤러에서 리스트 달라고 요청
async function getCommentListFromServer(bno) {
  try {
    const resp = await fetch("/cmt/list/" + bno);  // /cmt/list/343
    const result = await resp.json();  // '[{..},{..},{..}]'  =>   [{..},{..},{..}]
    return result;
  } catch (error) {
    console.log(error);
  }
}

function printCommentList(bno) {
  getCommentListFromServer(bno).then(result => {  // cmtList
    console.log(result);
    if (result.length > 0) {
      spreadCommemtList(result);
    } else {
      let div = document.getElementById('commentLine');
      div.innerHTML = `<div> comment가 없습니다. </div>`;
    }
  })
}

function spreadCommemtList(result) { // result => 댓글 리스트
  console.log("comment List >> " + result);
  let div = document.getElementById('commentLine');
  div.innerHTML = '';  // 원래 만들었던 구조 지우기
  for (let i = 0; i < result.length; i++) {
    let html = `<div>`;
    html += `<div>${result[i].cno}, ${result[i].bno}, ${result[i].writer}, ${result[i].regdate}</div>`;
    html += `<div>`;
    html += `<input type="text" class="cmtText" value="${result[i].content}">`;
    if (result[i].writer == userLoginInfo) {
      html += `<button type="button" data-cno="${result[i].cno}" class="cmtModBtn">수정</button>`;
      html += `<button type="button" data-cno="${result[i].cno}" class="cmtDelBtn">삭제</button>`;
    }
    html += `</div></div><br>`;
    html += `<hr>`;
    div.innerHTML += html;  // 각 댓글 객체를 누적해서 담기
  }

}

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

    // 댓글 등록 비동기 통신 호출
    postCommentToServer(cmtData).then(result => {
      console.log(result);
      if (result > 0) {
        alert("댓글등록 성공~!!");
        document.getElementById('cmtText').value = '';
      }
      // 댓글출력
      printCommentList(bnoVal);
    })

  }
});

async function postCommentToServer(cmtData) {
  try {
    const url = "/cmt/post";
    const config = {
      // method, headers, body
      // body가 없으면 header도 없음, 헤더에 바디 정보를 담고 있기에
      method: 'post',
      Headers: {
        'content-Type': 'application/json; charset=utf-8'
      },
      body: JSON.stringify(cmtData)
    };

    const resp = await fetch(url, config);
    const result = await resp.text();  // isOk 값을 리턴
    return result;

  } catch (error) {
    console.log(error);
  }
}

// 수정 : cno, content  =>  result isOk
async function updateCommentFromServer(cnoVal, cmtText) {
  try {
    const url = "/cmt/modify";
    const config = {
      method: 'post',
      Headers: {
        'content-Type': 'application/json; charset=utf-8'
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

// 삭제 : cno  =>  result isOk
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

document.addEventListener('click', (e) => {
  console.log(e.target);
  // 삭제버튼이 클리되면...
  if (e.target.classList.contains('cmtDelBtn')) {
    let cnoVal = e.target.dataset.cno;
    console.log(cnoVal);
    removeCommentFromServer(cnoVal).then(result => {
      if (result > 0) {
        alert('댓글 삭제 성공!');
        printCommentList(bnoVal);
      }
    })
  }
  // 수정버튼이 클릭되면...
  if (e.target.classList.contains('cmtModBtn')) {
    let cnoVal = e.target.dataset.cno;
    console.log(cnoVal);
    let div = e.target.closest('div');
    // 타겟을 기준으로 가장 가까운 div 찾기
    console.log(div);
    let cmtText = div.querySelector('.cmtText').value;
    console.log(cmtText);
    updateCommentFromServer(cnoVal, cmtText).then(result => {
      if (result > 0) {
        alert('댓글 수정 성공!');
        printCommentList(bnoVal);
      }
    })
  }

})