/* Read LICENSE.TXT for this mod's LICENSE! */
package minetrax;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import java.io.IOException;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public final class MineTraxAlbumMaker {

    public int numberOfDiscs;
    int arrayDisc[];
    public static Item newDisc;
    public String songAuthor;
    public String songTitle;
    public String songFilename;
    public int songGFXID;
    public Configuration albumConfigFile;

    /**
     * Loads the albums Notes: This method will forever be named doStuffs in
     * memory of DarkSnake's old conventions that were riddled through this mod.
     *
     * [Note from DarkSnake: I initially intended to make it so we load multiple
     * albums, but I kinda scrapped the idea... if anyone's up for the job, then
     * go ahead! :)]
     *
     * [Note from DarkSnake, again: Stuffs is my convention. Also, it's not old.
     * Suck it, Flaeme ♥] Please do not merge the album config file with the
     * mod's config file!
     *
     * @param config
     * @throws IOException
     */
    public void doStuffs() throws IOException {
        albumConfigFile = new Configuration(new File("config/MineTrax_album.cfg"));
        albumConfigFile.load();

        numberOfDiscs = Integer.parseInt(albumConfigFile.get("General", "NumberOfDiscs", 10).value);



        arrayDisc = new int[numberOfDiscs];
        for (int curDisc = 0; curDisc < arrayDisc.length; curDisc++) {
            arrayDisc[curDisc] = curDisc;
            int discID = arrayDisc[curDisc] + DataProxyMineTrax.discStartID;
            String curDiscStr = Integer.toString(curDisc);
            songAuthor = albumConfigFile.get(curDiscStr, "SongAuthor", "None").value;
            songTitle = albumConfigFile.get(curDiscStr, "SongTitle", "None").value;
            songFilename = albumConfigFile.get(curDiscStr, "SongFilename", "None").value;
            songGFXID = Integer.parseInt(albumConfigFile.get(curDiscStr, "SongGFXID", 1).value);

            newDisc = new ItemMineTraxRecord(discID, songFilename, songAuthor, songTitle).setIconIndex(1).setItemName("MusicDisc" + curDisc);
            GameRegistry.registerItem(newDisc, "MusicDisc" + curDiscStr);
            LanguageRegistry.addName(newDisc, "Music Disc");
        }

        albumConfigFile.save();
    }
}