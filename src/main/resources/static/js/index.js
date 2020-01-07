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
    tile.setAttribute('tileid', ID);
    switch (ID){
        case "STEPPE":
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_desert_flat_01.png');
            break;
        case "GREEN":
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_boreal_flat_01.png');
            break;
        case "WATER":
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_wdeep_flat_01.png');
            break;
        case "STONE":
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_stone1_hill_01.png');
            break;
    }
}

function switchUnitByID(node, ID){
    switch (ID){
        case "HERBIVORE":
            node.setAttribute('src', 'static/img/herbivore.png');
            break;
        case "PREDATOR":
            node.setAttribute('src', 'static/img/predator.png');
            break;
        case "HUMAN":
            node.setAttribute('src', 'static/img/human.png');
            break;
        case "TREE":
            node.setAttribute('src', 'static/img/tree.png');
            break;
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
    let response = await fetch('http://localhost:4567/api/v1/units/');
    units = await response.json();

    let map = document.getElementById("map");

    for (let i = 0; i < h; i++){
        for (let j = 0; j < w; j++){
            let tile = map.childNodes[i].childNodes[j];
            switchImageByID(tile, tiles[i][j].tileType);
        }
    }

    let node;
    for (let unit of units){
        try {
            node = map.childNodes[unit.y].childNodes[unit.x];
        } catch (e){
            console.log(e.message);
        }
        if (node == undefined) continue;
        switchUnitByID(node, unit.unitType);
    }
}

async function updateTime(){
    let time = document.getElementById("time");
    time.innerText = day++ + " days";
}

document.addEventListener('DOMContentLoaded', async function () {
    constructMap();
    await sleep(5000);

    setInterval(constructMap, 10000);
    setInterval(updateUnits, 250);
    setInterval(updateTime, 1000);
});
