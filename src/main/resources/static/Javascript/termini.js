$(document).ready(function () {
    $.ajax({
        type: "GET",                                              
        url: "http://localhost:8080/api/termini/prikaz",                 
        dataType: "json",                                          
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
    let pretraga = {
        nazivTreninga, 
        opisTreninga,
        tipTreninga,
        cenaTermina,
        pocetakTermina,
    }
    let toEmpty = $("#prikaz");
    toEmpty.empty();
    $.ajax({
        type: "POST",                                                
        url: "http://localhost:8080/api/termini/pretraga",                 
        dataType: "json",            
        contentType: "application/json",              
        data: JSON.stringify(pretraga),                                       
        success: function (res) {                                
            console.log("SUCCESS:\n", res);        
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

