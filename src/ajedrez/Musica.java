package  ajedrez;

import uwcse.io.Sound;
import java.io.File;

/**
 * Esta clase extrae un sonido de la carpeta sonidos
 * @author maria
 */
public class Musica {
    /**
     * Se extrae el sonido como un objeto de la clase Sound
     * @param musica 
     */
    public Musica(String musica){
        sonido = new Sound(this.getClass().getClassLoader().getResource("resources"+File.separator+"sonidos"+File.separator+musica));
}
    
    /**
     * Reproduce el sonido
     **/
    public void star(){
    sonido.getClip();
    sonido.play();
            
}
    //Se instancia el sonido
private final Sound sonido;

}