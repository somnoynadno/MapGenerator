class Game {
    constructor() {
        this._mapWidth = 50;
        this._mapHeight = 50;
        this._map = document.getElementById("map");
        this._constructMap();
    }
    _constructMap() {
        for (let i = 0; i < this._mapHeight; i++) {
            let row = document.createElement("div");
            row.classList.add("row");
            for (let j = 0; j < this._mapWidth; j++) {
                let img = document.createElement("img");
                img.setAttribute('src', 'static/tiles/grid/hexset_grid_boreal_flat_01.png');
                row.appendChild(img);
            }
            this._map.appendChild(row);
        }
    }
}
document.addEventListener('DOMContentLoaded', function () {
    let game = new Game();
});
