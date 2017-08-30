function test(){
	alert("iii");
	searchForm();
}
function searchForm()
{
    if(document.searchForm.onsubmit &&
    !document.searchForm.onsubmit()) //判断是否正在提交，如果是则直接返回
    {
        return;
    }
 document.searchForm.submit(); //调用form的submit()方法完成表单的提交
}
