
function _clear(obj) {
	obj.form.reset();
	document.getElementById("pbar").value = 0;
	d = new Date();
	dd = d.getDate()+1;
	mm = d.getMonth()+1;
	if(dd < 10) dd = "0"+dd;
	if(mm < 10) mm = "0"+mm;
	document.getElementById("date").min = ""+(d.getFullYear()+"-"+mm+"-"+dd);
	document.getElementById("date").value = ""+(d.getFullYear()+"-"+mm+"-"+dd);
	window.location = "#";
	return false;
}
function calculate() {
	inp = parseFloat(document.getElementById("inp").value);
	int = parseFloat(document.getElementById("int").value);
	pro = parseFloat(document.getElementById("pro").value);
	com = parseFloat(document.getElementById("com").value);
	val = parseFloat(document.getElementById("val").value);
	res = parseFloat(document.getElementById("res").value);
	bar = parseFloat(document.getElementById("pbar").value);
	date = Date.parse(document.getElementById("date").value);
	cdate = Date.now() - (Date.now()%(24000*3600));
	elemarr = [ inp, int, pro, com, val, date, ];
	t = 0;
	for(i=0;i<6;i++)
	{
		if(!elemarr[i])
			continue;
		t += 1;
		continue;
	}
	document.getElementById("pbar").max = 6;
	document.getElementById("pbar").value = t;
	if(t/6 < 1) return;
	def = 36000.0;
	if(int < 9) int = int*12.0;
	out = ((inp*int) * (Math.abs(cdate-date) / (24000*3600) + val) / def);
	out = (com*inp/100.0 + pro) + out;
	document.getElementById("res").value = parseFloat( inp - ((5.0*out/100.0)+out) ).toFixed(2);
	return;
}
//Faiz Kesintisi  = Tutar * Yıllk Faiz Oranı (Faiz) * Gün ( Vade + Valör)/ 36000
window.onload = function() {
	d = new Date();
	dd = d.getDate()+1;
	mm = d.getMonth()+1;
	if(dd < 10) dd = "0"+dd;
	if(mm < 10) mm = "0"+mm;
	document.getElementById("pbar").value = 0;
	document.getElementById("name").placeholder = "";
	document.getElementById("date").min = ""+(d.getFullYear()+"-"+mm+"-"+dd);
	document.getElementById("date").value = ""+(d.getFullYear()+"-"+mm+"-"+dd);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (xhr.readyState != 4) {
			return;
		}
		lines = xhr.responseText.split("\n");
		w = [ parseInt(1+Math.random()*(lines.length-1)),
			parseInt(1+Math.random()*(lines.length-1)),
			parseInt(1+Math.random()*(lines.length-1)),
		];
 		document.getElementById("name").value = lines[w[0]] + " " + lines[w[1]];
	};
	r = 1536;
	xhr.open('GET', 'trlist-orig.txt', true);
	b = parseInt(Math.random()*(609249-r));
	xhr.setRequestHeader('Range', 'bytes='+b+'-'+(b+r));
	xhr.send(null);
}

/*
Faiz = Ana para x faiz x gun / 36.000
Gun = vade + valor
Komisyon = Anapara x %oran
Provizyon = 0 tl - 100 tl arası
Bsmh( kdv) = ( faiz+provizyon+ komisyon) x %5
*/
