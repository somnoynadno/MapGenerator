'use strict'

let w = 0;
let h = 0;
let day = 0;

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function switchImageByID(tile, ID){
    tile.setAttribute('tileid', ID);
    switch (ID){
        case 1:
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_desert_flat_01.png');
            break;
        case 2:
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_boreal_flat_01.png');
            break;
        case 3:
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_wdeep_flat_01.png');
            break;

    }
}

async function constructMap(){
    let response = await fetch('http://localhost:4567/api/v1/map/');
    let json = await response.json();

    w = json.width;
    h = json.height;
    temperature = json.temperature;
    let tiles = json.tiles;

    let temp = document.getElementById('temperature');
    temp.innerText = temperature + "°C";

    let map = document.getElementById("map");
    map.innerHTML = ''; // clear map

    for (let i = 0; i < h; i++) {
        let row = document.createElement("div");
        row.classList.add("row");
        for (let j = 0; j < w; j++) {
            let img = document.createElement("img");
            switchImageByID(img, tiles[i][j].id);

            img.setAttribute('X', j);
            img.setAttribute('Y', i);

            img.addEventListener('click', async function(){
                let x = img.getAttribute('X');
                let y = img.getAttribute('Y')
                let response = await fetch('http://localhost:4567/api/v1/info/?x=' + x + "&y=" + y);
                let json = await response.json();
                console.log(json);
            });

            row.appendChild(img);
        }
        map.appendChild(row);
    }
}

async function updateMap(){
    let response = await fetch('http://localhost:4567/api/v1/tiles/');
    let tiles = await response.json();

    let map = document.getElementById("map");

    for (let i = 0; i < h; i++){
        for (let j = 0; j < w; j++){
            let tile = map.childNodes[i].childNodes[j];
            if (tile.tileid != tiles[i][j].id){
                switchImageByID(tile, tiles[i][j].id);
            }
        }
    }
}

async function updateAnimals(){
    let response = await fetch('http://localhost:4567/api/v1/animals/');
    let animals = await response.json();

    let map = document.getElementById("map");

    let node;
    for (let animal of animals){
        try {
            node = map.childNodes[animal.y].childNodes[animal.x];
        } catch (e){
            console.log(e.message);
        }
        if (node == undefined) continue;
        switch (animal.id){
            case 1:
                node.setAttribute('src', 'static/img/herbivore.png');
                break;
            case 2:
                node.setAttribute('src', 'static/img/predator.png');
                break;
            case 3:
                node.setAttribute('src', 'static/img/human.png');
                break;
            case 10:
                node.setAttribute('src', 'static/img/tree.png');
                break;
        }
    }
}

async function updateTime(){
    let time = document.getElementById("time");
    time.innerText = day++ + " days";
}

document.addEventListener('DOMContentLoaded', async function () {
    setTimeout(constructMap, 1000);
    setInterval(updateMap, 1000);

    await sleep(1000);

    setInterval(updateAnimals, 50);
    setInterval(updateTime, 1000);
});
