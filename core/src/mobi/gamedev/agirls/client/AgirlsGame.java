package mobi.gamedev.agirls.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class AgirlsGame extends ApplicationAdapter implements InputProcessor{

	Stage stage;
	Skin skin;

	private Table table;
	private TextButton startBtn;
	private TextButton exitBtn;
    int screenH;
    int screenW;

    private SpriteBatch batch;
    private Sprite sprite;

	@Override
	public void create () {
        screenH = Gdx.graphics.getHeight();
        screenW = Gdx.graphics.getWidth();
        skin = new Skin(Gdx.files.internal("skins/green/skinUI.json"));
		stage = new Stage(new ScreenViewport());

		table = new Table();
		table.setWidth(stage.getWidth());
		table.align(Align.center|Align.top);
		table.setPosition(0, screenH/2);

        startBtn = new TextButton("New Game", skin);
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked Start");
            }
        });

		exitBtn = new TextButton("Quit Game", skin);
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked Exit");
            }
        });

		table.add(startBtn);
		table.add(exitBtn);

		stage.addActor(table);

		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		sprite.setSize(screenW, screenH);

        InputMultiplexer im = new InputMultiplexer(stage, this);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
        table.setWidth(stage.getWidth());
        table.setPosition(0, stage.getHeight() / 2);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    sprite.setFlip(!sprite.isFlipX(), sprite.isFlipY());
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
