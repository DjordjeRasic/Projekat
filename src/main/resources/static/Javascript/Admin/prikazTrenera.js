$(document).ready(function () {   
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 2){
        window.location.href = "../Trener/terminiTrenera.html";
    } else if (role == 3){
        window.location.href = "../Clan/clan.html";
    }  
    
    $.ajax({
        type: "GET",                                                
        url: "http://localhost:8080/api/trener/treneri/" + role,                 
        dataType: "json",                                           
        success: function (res) {                                   
                                     
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'red" + res[i].id +"'>";                                   
                row += "<td>" + res[i].id + "</td>";         
                row += "<td>" + res[i].korisnickoIme + "</td>";
                row += "<td>" + res[i].ime + "</td>";
                row += "<td>" + res[i].prezime + "</td>";         
                row += "<td>" + res[i].centarID + "</td>";
                row += "<td>" + substring(res[i].datumRodjenja) + "</td>";                                      
                let btn = "<td><button id = 'delete' class = 'deleteButton' type = 'submit' data-id=" + res[i].id + ">OBRISI</button></td>";
                row += btn;                      
                row += "</tr>";                                     
                $('#prikazTrenera').append(row);                        
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});
function substring(string) {
    return `${string.substring(0,10)} ${string.substring(11, 16)}`;
}
$(document).on('click', '#delete', function () {            
                                   
    
    let id = this.dataset.id;
    let brisanje = "#red";
    brisanje += id;

    let sakrij = $(brisanje);                      
    sakrij.hide();


    
    $.ajax({
        type: "PUT",                                               
        url: "http://localhost:8080/api/trener/deaktiviraj/" + id,                 
        dataType: "json",                                           
        contentType: "application/json",
        success: function (res) {                               
            alert(res.poruka);                                
        },
        error: function (res) {                                
            console.log("ERROR:\n", res);
        }
    });
});

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}