'use strict'

let w = 0;
let h = 0;
let day = 0;
let tiles;
let units;

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function switchImageByID(tile, ID){
    switch (ID){
        case "STEPPE":
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_desert_flat_01.png');
            break;
    }
}

function switchUnitByID(node, unit){
    let ID = unit.unitType;
    switch (ID){
        case "GRASS":
            node.setAttribute('src', 'static/tiles/grid/hexset_grid_boreal_flat_01.png');
            break;
        case "PUMA":
            node.setAttribute('src', 'static/img/puma.png');
            break;
        case "ZEBRA":
            node.setAttribute('src', 'static/img/zebra.png');
            break;
        case "FOX":
            node.setAttribute('src', 'static/img/fox.png');
            break;
        case "RABBIT":
            node.setAttribute('src', 'static/img/rabbit.png');
            break;
        case "HUMAN":
            node.setAttribute('src', 'static/img/human.png');
            break;
        case "GIRAFFE":
            node.setAttribute('src', 'static/img/giraffe.png');
            break;
        case "BEAR":
            node.setAttribute('src', 'static/img/bear.png');
            break;
        case "TREE":
            node.setAttribute('src', 'static/img/tree.png');
            break;
        case "WHEAT":
            node.setAttribute('src', 'static/img/wheat.png');
            break;
    }
}

function drawHouse(house){
    let map = document.getElementById("map");

    let x = house.x;
    let y = house.y;

    let hs = 1;

    if (house.unitType == "HOUSE") {
        hs = 1;
    } else if (house.unitType == "FARM") {
        hs = 2;
    }

    for (let j = y-hs; j <= y+hs; j++){
        for (let i = x-hs; i <= x+hs; i++){
            try {
                let node = map.childNodes[j].childNodes[i];
                node.setAttribute('src', 'static/tiles/grid/hexset_grid_stone1_hill_01.png');
            } catch (e) {
                console.log(e.message);
            }
        }
    }
}

async function constructMap(){
    let response = await fetch('http://localhost:4567/api/v1/map/');
    let json = await response.json();

    w = json.width;
    h = json.height;
    let temperature = json.temperature;
    tiles = json.tiles;

    let temp = document.getElementById('temperature');
    temp.innerText = temperature + "°C";

    let map = document.getElementById("map");
    map.innerHTML = ''; // clear map

    for (let i = 0; i < h; i++) {
        let row = document.createElement("div");
        row.classList.add("row");
        for (let j = 0; j < w; j++) {
            let img = document.createElement("img");
            switchImageByID(img, tiles[i][j].tileType);

            img.setAttribute('X', j);
            img.setAttribute('Y', i);

            img.addEventListener('click', function(){
                for (let unit of units){
                    if (unit.x == j && unit.y == i){
                        console.log(unit);
                        switchUnitByID(document.getElementById("unit-img"), unit.unitType)
                        document.getElementById("unit-name").innerText = unit.unitType
                        document.getElementById("unit-coords").innerText = '(' + unit.x + "; " + unit.y + ')';
                        if (unit.hunger){
                            document.getElementById("unit-hunger").innerText = "Hunger: " + unit.hunger;
                        } else document.getElementById("unit-hunger").innerText = ''
                        return;
                    }
                }
                console.log("No unit on position")
            });

            row.appendChild(img);
        }
        map.appendChild(row);
    }
}

async function updateUnits(){
    let prevUnits = units;

    let response = await fetch('http://localhost:4567/api/v1/units/');
    units = await response.json();

    console.log("Units fetched");

    let node;
    for (let unit of prevUnits){
        try {
            node = map.childNodes[unit.y].childNodes[unit.x];
        } catch (e){
            console.log(e.message);
        }
        if (node == undefined) continue;
        switchImageByID(node, "STEPPE");
    }

    for (let unit of units){
        try {
            node = map.childNodes[unit.y].childNodes[unit.x];
        } catch (e){
            console.log(e.message);
        }
        if (node == undefined) continue;
        switchUnitByID(node, unit);
    }
}

async function updateHouses(){
    let response = await fetch('http://localhost:4567/api/v1/houses/');
    let houses = await response.json();

    for (let house of houses) {
        drawHouse(house);
    }
}

async function updateTime(){
    let time = document.getElementById("time");
    time.innerText = day++ + " days";
}

document.addEventListener('DOMContentLoaded', async function () {
    constructMap();
    await sleep(1000);

//    setInterval(constructMap, 40000)
    setInterval(updateHouses, 2000);
    setInterval(updateUnits, 800);
    setInterval(updateTime, 1000);
});
