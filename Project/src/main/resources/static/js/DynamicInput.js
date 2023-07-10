var value=1;

function dynamic_func(){
value++;

html='<div class="row" id="row'+value+'">\
      <div class="col-3">\
          <label class="text-white">'+value+'.Metin</label>\
          <input type = "text" name = "text'+value+'" class = "form-control" placeholder="'+value+'.Metni giriniz" >\
</div>\
</div>'

var temp= document.getElementById('text_input');
temp.innerHTML+=html;









}