'use strict'

let w = 0;
let h = 0;

function switchImageByID(tile, ID){
    tile.setAttribute('tileid', ID);
    switch (ID){
        case 1:
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_desert_flat_01.png');
            break;
        case 2:
            tile.setAttribute('src', 'static/tiles/grid/hexset_grid_boreal_flat_01.png');
            break;
    }
}

async function constructMap(){
    let response = await fetch('http://localhost:4567/api/v1/map/');
    let json = await response.json();

    w = json.width;
    h = json.height;
    let tiles = json.tiles;

    let map = document.getElementById("map");
    map.innerHTML = ''; // clear map

    for (let i = 0; i < h; i++) {
        let row = document.createElement("div");
        row.classList.add("row");
        for (let j = 0; j < w; j++) {
            let img = document.createElement("img");
            switchImageByID(img, tiles[i][j].id);
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

document.addEventListener('DOMContentLoaded', async function () {
    setTimeout(constructMap, 1000);
    setInterval(updateMap, 1000);
});
