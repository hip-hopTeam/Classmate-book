function setAnima() {
    if (!document.getElementById) return false;
    if (!document.getElementsByClassName) return false;
    if(!document.getElementById("title")) return false;
    var oldclass = null;
	var title = document.getElementById("title");
    if(!document.getElementById("desc")) return false;
	var desc = document.getElementById("desc");
	var wordInput = document.getElementsByClassName("words");
	var clickInput = document.getElementsByClassName("click");
	if (!title.className){
		oldclass = "";
	}else{
		oldclass = title.className;
	}
	title.className = "animated flipInX " + oldclass;
    if (!desc.className){
        oldclass = "";
    }else{
        oldclass = desc.className;
    }
    desc.className = "animated flipInX " + oldclass;
    for(var i=0;i<wordInput.length;i++)
	{
        if (!wordInput[i].className){
            oldclass = "";
        }else{
            oldclass = wordInput[i].className;
        }
        wordInput[i].className = "animated fadeInRight " + oldclass;
	}
    for(var j=0;j<clickInput.length;j++)
    {
        if (!clickInput[j].className){
            oldclass = "";
        }else{
            oldclass = clickInput[j].className;
        }
        clickInput[j].className = "animated fadeIn " + oldclass;
    }
}

window.onload = setAnima;