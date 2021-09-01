$(document).ready(function () { 
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 2){
        window.location.href = "../Trener/terminiTrenera.html";
    } else if (role == 1){
        window.location.href = "../Admin/prikazTrenera.html";
    }     
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var provera = {
        clanID, 
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/odradjeni",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                              
        success: function (res) {                                      
            for (i = 0; i < res.length; i++) {    
                if(res[i].ocena == -1) {
                    res[i].ocena = "-";
                    var btn = "<button class='deleteButton' id='gradeButton' data-id=" + res[i].id + " data-o=" + res[i].ocena + ">Oceni</button>";
                } else {
                    var btn = "OCENJEN";
                }

                let row = "<tr>";
                row += "<td>" + res[i].id + "</td>";    
                row += "<td>" + res[i].ocena + "</td>";    
                row += "<td>" + res[i].nazivTreninga + "</td>";           
                row += "<td>" + res[i].nazivSale + "</td>";     
                row += "<td>" + res[i].nazivCentra + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";
                row += "<td>" + res[i].nazivTrenera + "</td>";
                
                row += "<td>" + btn + "</td>";                          
                row += "</tr>";                                     
                $('#odradjeni').append(row);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#gradeButton', function () {
    localStorage.setItem("termin-id", this.dataset.id);
    if(this.dataset.o !== "-") {
        alert("Već ste ocenili ovaj termin!");
        return false;
    }
    let open = $("#ocenaDiv");
    open.show();
});
$(document).on("submit", "form", function (event) {  
    event.preventDefault();
    let ocena =  document.forms['ocena'].ocena.value;
    if(ocena < 0 || ocena > 5 || isNaN(ocena)){
        alert("Niste uneli validnu ocenu (0-5)!")
        return false;
    }
    let clanID = localStorage.getItem("id");
    let terminID = localStorage.getItem("termin-id");
    let ocenaPack = {
        ocena,
        clanID,
        terminID
    }
    $.ajax({
        type: "POST",                                               
        url: "http://localhost:8080/api/ocena/oceni/",                 
        dataType: "json",                                           
        contentType: "application/json",                            
        data: JSON.stringify(ocenaPack),                          
        success: function (res) {                                   
            alert(res.poruka);
            window.location.href = "odradjeniTermini.html";
            
        },
        error: function () {                                        
            alert("Greška!");
        }
    });
});

function closeOcenaDiv(){
    let close = $("#ocenaDiv");
    close.hide();
}

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}