var animal_from_server;

function Animal()
{
this.name;
this.species;
this.weigth;
this.birthyear;
}

function get_animals()
{
 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/animals' ,
    type: "GET",
    dataType: 'json',
    success: function(data) {
    	display_animals(data);
        console.log(JSON.stringify(data));

    }
  });


}


function get_animal(id)
{

 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/animal/' + id ,
    type: "GET",
    dataType: 'json',
    success: function(data) {
    	animal_from_server = data;
    	display_animal(data);
        console.log(JSON.stringify(data));

    }
  });


}


function save_animal()
{
	
	animal_from_server.name=document.forms[0].name.value;
	animal_from_server.species=document.forms[0].species.value;
	animal_from_server.weigth=document.forms[0].weigth.value;
	animal_from_server.birthyear=document.forms[0].birthyear.value;
		
var jsonData = JSON.stringify(animal_from_server); 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/animal/' + animal_from_server.id ,
    type: "POST",
    data: jsonData,
    dataType: 'json',
    contentType : 'application/json',
    success: function(data) {
    	show_message("Salvestatud");
        console.log(JSON.stringify(data));

    }
  });


}






function display_animal(animal)
{
	 var out_data="";
	 out_data = out_data + "<table bgcolor=eeeeee><tr><td>Muutmine. Loom id: <b>" + animal.id + "</b></td></tr>";

		out_data = out_data + "<tr><td>Nimi:</td><td><input type=text name=name value='" + animal.name + "'></td></tr>";
		out_data = out_data + "<tr><td>Liik:</td><td><input type=text name=species value='" + animal.species + "'></td></tr>";
		out_data = out_data + "<tr><td>Kaal:</td><td><input type=text name=weigth value='" + animal.weigth + "'></td></tr>";
		out_data = out_data + "<tr><td>Synniaasta:</td><td><input type=text name=birthyear value='" + animal.birthyear + "'></td></tr>";
		out_data = out_data + "<td><button type='button' class='btn'  onClick='javascript:save_animal()'>Salvesta</button></td>";
		out_data = out_data + "</table>";

	

	
	 $("#one_animal").html(out_data);
}




function display_animals(data)
{
	var out_data="";
	 out_data = out_data + "<table bgcolor=eeeeee><tr><td colspan=4>Loomi kokku: <b>" + data.length + "</b></td></tr>";
	 for(var  i in data) {
   	  var animal = data[i];
		out_data = out_data + "<tr><td>name:</td><td bgcolor=ffffff>" + animal.name+ "</td><td>liik:</td><td bgcolor=ffffff>" + animal.species + "</td>";
		out_data = out_data + "<td><button type='button' class='btn'  onClick='javascript:get_animal(" + animal.id + ")'>Vali</button></td>";
		out_data = out_data + "<td><button type='button' class='btn'onClick='javascript:delete_animal(" + animal.id + ")'>Kustuta</button></td></tr>";
	 }
	 out_data = out_data + "</table>";

	
	 $("#animals_table").html(out_data);
}

function delete_animal(id)
{
	$.ajaxSetup({ cache: false });
	$.ajax({
		url: 'service/animal/' + id ,
		type: "DELETE",
		contentType : 'application/json',
		success: function(data) {
			 show_message("Kustutatud");
			 get_animals();
			 console.log(JSON.stringify(data));
		}
	});
}

function add_animal()
{
	var animal_to_server = new Animal();
	animal_to_server.name=document.forms[0].new_animal_name.value;
	animal_to_server.species=document.forms[0].new_animal_species.value;
	animal_to_server.weigth=document.forms[0].new_animal_weigth.value;
	animal_to_server.birthyear=document.forms[0].new_animal_birthyear.value;
	var jsonData = JSON.stringify(animal_to_server);
	$.ajaxSetup({ cache: false });
	$.ajax({
		 url: 'service/animal/' ,
		 type: "PUT",
		 data: jsonData,
		 dataType: 'json',
		 contentType : 'application/json',
		 success: function(data) {
			 show_message("Sisestatud");
			 console.log(JSON.stringify(data));
			 get_animals();
		 }
	 });
}




function show_message(message)
{
	
	 $("#msg_text").html(message);
}
