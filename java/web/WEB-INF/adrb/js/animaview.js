function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof oldonload != 'function'){
        window.onload = func;
    }else{
        window.onload = function () {
            oldonload();
            func();
        }
    }
}

function animaSet() {
    if (!document.getElementById) return false;
    if (!document.getElementsByClassName) return false;
    if (!document.getElementsByTagName) return false;
    var oldclass = null;
    if (!document.getElementById("title")) return false;
    var title = document.getElementById("title");
    if (!document.getElementById("list")) return false;
    var list = document.getElementById("list");
    var link = document.getElementsByTagName("a");
    if (!title.className){
        oldclass = "";
    }else{
        oldclass = title.className;
    }
    title.className = "animated flipInX " + oldclass;
    if (!list.className){
        oldclass = "";
    }else{
        oldclass = list.className;
    }
    list.className = "animated fadeIn " + oldclass;
    for(var i=0;i<link.length;i++)
    {
        if (!link[i].className){
            oldclass = "";
        }else{
            oldclass = link[i].className;
        }
        link[i].className = "animated fadeInUp " + oldclass;
    }
}

addLoadEvent(animaSet);