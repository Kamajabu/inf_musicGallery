package com.kamajabu.infmusicgallery.musicmanager;

import android.content.Context;
import android.os.Environment;

import com.kamajabu.infmusicgallery.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
    // SDCard Path
    final String MEDIA_PATH = new String(getSDPath() + "/Download");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

    // Constructor
    public SongsManager() {

    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        } else {
            sdDir = Environment.getRootDirectory();
        }
        return sdDir.toString();
    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     */
    public ArrayList<HashMap<String, String>> getPlayListFromFile() {
        File home = new File(MEDIA_PATH);

        System.out.println(home);

        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap<String, String> song = new HashMap<String, String>();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());

                // Adding each song to SongList
                songsList.add(song);
            }
        } else {
            HashMap<String, String> song = new HashMap<String, String>();
            song.put("songTitle", "123");
            song.put("songPath", "/");
            songsList.add(song);
        }
        // return songs list array
        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3") || name.endsWith(".ogg") || name.endsWith(".OGG"));
        }
    }


    public ArrayList<HashMap<String, String>> getPlayListFromContent(Context mediaContext) {
        ArrayList<HashMap<String, String>> listAudio = new ArrayList<>();


        String[] names = {"birthday",  "black ant", "box cat",  "energy", "faithful_dog", "humsafar",
                "jason_shaw", "night_owl", "romantic", "siesta", "siri", "springish"};

        int[] ids = {R.raw.birthday, R.raw.black_ant, R.raw.box_cat, R.raw.energy, R.raw.faithful_dog,
                R.raw.humsafar, R.raw.jason_shaw, R.raw.night_owl, R.raw.romantic, R.raw.siesta, R.raw.siri,
                R.raw.springish };

        for(int i = 0; i<names.length; i++) {
            HashMap<String, String> song = new HashMap<>();
            song.put("songTitle", names[i]);
            song.put("songPath", String.valueOf(ids[i]));
            listAudio.add(song);
        }
        return listAudio;
    }
}
