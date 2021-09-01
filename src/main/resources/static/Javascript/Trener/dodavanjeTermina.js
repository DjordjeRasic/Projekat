$(document).ready(function () { 
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 1){
        window.location.href = "../Admin/prikazTrenera.html";
    } else if (role == 3){
        window.location.href = "../Clan/clan.html";
    }
    var zastita = localStorage.getItem("role");
    var id = localStorage.getItem("id");

    var provera = {
        zastita,
        id
    }
    
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/trener/prikazSpiskova",                
        dataType: "json",
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                       
        success: function (res) {
            const nazivi = [];
            const sale = [];
            for (i = 0; i < res.length; i++) {    
                if(!sale.includes(res[i].nazivSale)) {
                    sale.push(res[i].nazivSale);
                    let sala = "<option>" + res[i].nazivSale + "</option>";   
                    $('#sala').append(sala); 
                }                   
                if(!nazivi.includes(res[i].nazivTreninga)) {
                    nazivi.push(res[i].nazivTreninga);
                    let naziv = "<option value='" + res[i].nazivTreninga + "'>" + res[i].nazivTreninga + "</option>";  
                    $("#naziv").append(naziv); 
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
    let pocetakTermina = $("#pocetak").val();
    let krajTermina = $("#kraj").val();
    let datum1 = pocetakTermina.substring(0, 10);
    let datum2 = krajTermina.substring(0, 10);
    console.log(datum1);
    console.log(datum2);
    if(datum1 !== datum2) {
        alert("Trening se mora završavati isti dan kada i počne!");
        return false;
    }
    let sati1 = cutString(pocetakTermina, 11, 13);
    let minute1 = cutString(pocetakTermina, 14, 16);
    let sati2 = cutString(krajTermina, 11, 13);
    let minute2 = cutString(krajTermina, 14, 16);
    let trajanjeTermina = (Number(sati2) * 60 + Number(minute2)) - (Number(sati1) * 60 + Number(minute1));
    let cenaTermina = $("#cena").val();
    let nazivTreninga = $("#naziv").val();
    let nazivSale = $("#sala").val();
    let zastita = localStorage.getItem("role");
    let id = localStorage.getItem("id");

    if(nazivTreninga == 0) {
        alert("Niste odabrali naziv treninga!");
        return false;
    }


    if(isNaN(cenaTermina)) {
        alert("Cena mora biti broj!");
        return false;
    }

    if(isNaN(trajanjeTermina)) {
        alert("Trajanje mora biti broj!");
        return false;
    }

    if(nazivSale == 0) {
        alert("Niste odabrali salu!");
        return false;
    }

    let noviTermin = {
        pocetakTermina,
        krajTermina,
        trajanjeTermina,
        cenaTermina,
        nazivTreninga,
        nazivSale,
        zastita,
        id
    }
    console.log(noviTermin);
    $.ajax({
        type: "POST",                                               
        url: "http://localhost:8080/api/trener/dodajTermin",                 
        dataType: "json",                                           
        contentType: "application/json",                            
        data: JSON.stringify(noviTermin),                          
        success: function (res) {                                   
            console.log(res);
            if(res.zastita == 4) {
                alert("Morate biti trener za ovu mogucnost!");
            } else if(res.zastita == 1) {
                alert("Pocetak termina upada u drugi termin");
            } else if(res.zastita == 2) {
                alert("Kraj termina upada u drugi termin");
            } else if(res.zastita == 3) { 
                alert("Termin preklapa drugi termin");
            } else { 
                alert("Termin je uspesno dodan!");
                window.location.href = "terminiTrenera.html";
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
    
});

function cutString(input, begin, end) {
    return input.substring(begin, end);
}

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}