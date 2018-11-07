var game = new Phaser.Game(1920, 800, Phaser.AUTO, 'phaser-example', { preload: preload, create: create });

function menu(){
    game = new Phaser.Game(1920, 800, Phaser.AUTO, 'phaser-example', { preload: preload, create: create });
}
function preload() {

    game.load.spritesheet('button', 'assets/Menu/button_sprite_sheet.png', 193, 71);
    game.load.spritesheet('buttonSalir', 'assets/Menu/button_sprite_sheet salir.png', 193, 71);
    game.load.spritesheet('buttonControles', 'assets/Menu/button_sprite_sheet controles.png', 193, 71);
    game.load.spritesheet('title', 'assets/Menu/TituloJuego.png');
    game.load.image('background','assets/Menu/Portada.png');

}

var button;
var buttonOnline;
var buttonExit;
var background;
var title;

function create() {

    nivelJuego = 0;
    console.log(nivelJuego);

    game.stage.backgroundColor = '#182d3b';

    background = game.add.tileSprite(0, 0, 1920, 800, 'background');

    title = game.add.sprite(game.world.centerX - 769, 50, 'title');

    button = game.add.button(game.world.centerX - 880, 550, 'button', actionOnClickGame, this, 2, 1, 0);
    buttonControles = game.add.button(game.world.centerX - 620, 550, 'buttonControles', actionOnClickControl, this, 2, 1, 0);
    buttonExit = game.add.button(game.world.centerX - 360, 550, 'buttonSalir', actionOnClickExit, this, 2, 1, 0); //game.world.centerX - 320, 550

    //buttonPruebas = game.add.button(game.world.centerX - 220, 550, 'buttonSalir', actionOnClickPruebas, this, 2, 1, 0); //game.world.centerX - 320, 550

    button.onInputOver.add(over, this);
    button.onInputOut.add(out, this);
    button.onInputUp.add(up, this);

}

function up() {
    //console.log('button up', arguments);
}

function over() {
    //console.log('button over');
}

function out() {
    //console.log('button out');
}

function actionOnClickGame () {
    game.destroy();
    inicio();

}

function actionOnClickControl () {
    game.destroy();
    controles();

}

function actionOnClickExit () {
    game.destroy();

}


function actionOnClickPruebas () {
    game.destroy();
    pruebas();

}