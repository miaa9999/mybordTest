function check(){
//    alert("버튼이 눌렸습니다.") // 메세지 박스를 보내는 함수
    if(document.getElementById("title").value.trim().length == 0){
    alert("제목이 입력되지 않았습니다.").focus();
    return false
    }

    if(document.getElementById("content").value.trim().length == 0){
        alert("내용이 입력되지 않았습니다.").focus();
        return false

        }
    alert("입력이 완료되었습니다.")
    document.getElementById("frm").submit()
    return true
}

function res(){
    document.getElementById("frm").reset();
    alert("처음부터 다시 입력합니다.").focus();


}