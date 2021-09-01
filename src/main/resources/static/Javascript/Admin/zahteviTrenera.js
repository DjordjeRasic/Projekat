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
        url: "http://localhost:8080/api/trener/zahtevi/" + role,                 
        dataType: "json",                                           
        success: function (res) {                                   
                                     
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'red" + res[i].id +"'>";                                   
                row += "<td>" + res[i].id + "</td>";         
                row += "<td>" + res[i].korisnickoIme + "</td>";
                row += "<td>" + res[i].ime + "</td>";
                row += "<td>" + res[i].prezime + "</td>";         
                row += "<td>" + res[i].centarID + "</td>";                                      
                let btn = "<td><button id = 'add' class = 'addButton' type = 'submit' data-id=" + res[i].id + ">ODOBRI</button></td>"
                         + "<td><button id = 'delete' class = 'deleteButton' type = 'submit' data-id=" + res[i].id + ">ODBIJ</button></td>";
                row += btn;                      
                row += "</tr>";                                     
                $('#zahteviTrenera').append(row);                        
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});
$(document).on('click', '#add', function () {            
                                        


    let id = this.dataset.id;
    let brisanje = "#red";
    brisanje += id;

    let sakrij = $(brisanje);                      
    sakrij.hide();

    
    $.ajax({
        type: "PUT",                                               
        url: "http://localhost:8080/api/trener/aktiviraj/" + id,                 
        dataType: "json",                                           
        contentType: "application/json",                            
        //data: JSON.stringify(empId),
        success: function (res) {
            alert(res.poruka);                                
        },
        error: function (res) {                                
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#delete', function () {            
                                   
    
    let id = this.dataset.id;
    let brisanje = "#red";
    brisanje += id;

    let sakrij = $(brisanje);                      
    sakrij.hide();


    
    $.ajax({
        type: "PUT",                                               
        url: "http://localhost:8080/api/trener/obrisi/" + id,                 
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