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
        url: "http://localhost:8080/api/clan/termini",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                       
        success: function (res) {                                   
            const nazivi = [];
            const opisi = [];
            const tipovi = [];
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr>";  
                let pocetak = substring(res[i].pocetakTermina);
                let kraj = substring(res[i].krajTermina);
                row += "<td>" + res[i].nazivTreninga + "</td>";
                row += "<td>" + res[i].opisTreninga + "</td>";
                row += "<td>" + res[i].tipTreninga + "</td>";                  
                row += "<td>" + pocetak + "</td>";         
                row += "<td>" + kraj + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";
                row += "<td>" + res[i].cenaTermina + "</td>";  
                let btn1 = "<button class='addButton' id='prijavaButton' data-id=" + res[i].id + ">Prijavi se</button>"
                row += "<td id='isprazni" + res[i].id + "'>" + btn1 + "</td>";
                let btn2 = "<button class='deleteButton' id='odabirButton' data-id=" + res[i].id + ">Odaberi</button>"
                row += "<td>" + btn2 + "</td>";                               
                row += "</tr>";                                     
                $("#prikaz").append(row);   
                if(!nazivi.includes(res[i].nazivTreninga)) {
                    nazivi.push(res[i].nazivTreninga);
                    let naziv = "<option value='" + res[i].nazivTreninga + "'>" + res[i].nazivTreninga + "</option>";  
                    $("#naziv").append(naziv); 
                }
                if(!opisi.includes(res[i].opisTreninga)) {
                    opisi.push(res[i].opisTreninga);
                    let opis = "<option value='" + res[i].opisTreninga + "'>" + res[i].opisTreninga + "</option>"; 
                    $("#opis").append(opis); 
                }
                if(!tipovi.includes(res[i].tipTreninga)) {
                    tipovi.push(res[i].tipTreninga);
                    let tip = "<option value='" + res[i].tipTreninga + "'>" + res[i].tipTreninga + "</option>"; 
                    $("#tip").append(tip);       
                }
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on("submit", "form", function (event) {  
    event.preventDefault();
    let cenaTermina =  document.forms['filter'].cena.value;
    let pocetakTermina = document.forms['filter'].datum.value;
    let nazivTreninga  = document.forms['filter'].naziv.value;
    let opisTreninga  = document.forms['filter'].opis.value;
    let tipTreninga  = document.forms['filter'].tip.value;  
    if(isNaN(cenaTermina) || cenaTermina === "") {
        cenaTermina = 99999;
    }
    if(pocetakTermina === "") {
        pocetakTermina = "2060-01-01";
    }
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    let pretraga = {
        nazivTreninga, 
        opisTreninga,
        tipTreninga,
        cenaTermina,
        pocetakTermina,
        clanID,
        zastita
    }
    $("#prikaz").empty();
    $.ajax({
        type: "POST",                                                
        url: "http://localhost:8080/api/clan/pretraga",                 
        dataType: "json",            
        contentType: "application/json",              
        data: JSON.stringify(pretraga),                                       
        success: function (res) {                 
            console.log("SUCCESS:\n", res);        
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id='toEmpty'>";
                let pocetak = substring(res[i].pocetakTermina);
                let kraj = substring(res[i].krajTermina);
                row += "<td>" + res[i].nazivTreninga + "</td>";
                row += "<td>" + res[i].opisTreninga + "</td>";
                row += "<td>" + res[i].tipTreninga + "</td>";                   
                row += "<td>" + pocetak + "</td>";         
                row += "<td>" + kraj + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";
                row += "<td>" + res[i].cenaTermina + "</td>";   
                let btn1 = "<button class='addButton' id='prijavaButton' data-id=" + res[i].id + ">Prijavi se</button>"
                row += "<td id='isprazni" + res[i].id + "'>" + btn1 + "</td>";
                let btn2 = "<button class='deleteButton' id='odabirButton' data-id=" + res[i].id + ">Odaberi</button>"
                row += "<td>" + btn2 + "</td>";         
                row += "</tr>";                                     
                $('#prikaz').append(row);
                console.log(res);                        
            }
        },
        error: function (res) {                                 
            console.log("ERROR:\n", res);
        }
        
    });
});


$(document).on('click', '#prijavaButton', function () {
    localStorage.setItem("termin-id", this.dataset.id);
    let string = "#isprazni" + this.dataset.id;
    $(string).empty();
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var terminID = this.dataset.id;
    var odjavljenTermin = {
        terminID,
        clanID,
        zastita
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/prijava",                 
        dataType: "json",
        contentType: "application/json",   
        data: JSON.stringify(odjavljenTermin),   
        success: function (res) {                         
            console.log("SUCCESS:\n", res);
            if(res.zastita == 1) {
                alert("Morate biti član da biste prijavili termin!");
            } else if(res.zastita == 2) {
                alert("Već ste prijavljeni na ovaj termin!");  
            } else if(res.zastita == 3) {
                alert("Ovaj termin je već prošao!"); 
            } else if(res.zastita == 4) {
                alert("Ovaj termin je popunjen!"); 
            } else {
                alert("Termin uspešno prijavljen!");
            }
        },
        error: function (res) {                               
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#odabirButton', function () {
  localStorage.setItem("termin-id", this.dataset.id);
  window.location.href = "terminDetalji.html";
});

function sort(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("prikazTermina");
  switching = true;
  dir = "asc"; 
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 2; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;      
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

function substring(string) {
    return `${string.substring(0,10)} ${string.substring(11, 16)}`;
}

function numberSort(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("prikazTermina");
  switching = true;
  dir = "asc"; 
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 2; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
        if (Number(x.innerHTML) > Number(y.innerHTML)) {
            shouldSwitch = true;
            break;
          }
      } else if (dir == "desc") {
        if (Number(x.innerHTML) < Number(y.innerHTML)) {
            shouldSwitch = true;
            break;
          }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;      
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

function odjaviSe() {
  window.location.href = "../../index.html";
  window.localStorage.setItem("role", 0); 
  window.localStorage.setItem("id", 0); 
}

