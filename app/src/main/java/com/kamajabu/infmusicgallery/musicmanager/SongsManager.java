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


        HashMap<String, String> song = new HashMap<>();
        song.put("songTitle", "birthday");
        song.put("songPath", String.valueOf(R.raw.birthday));
        listAudio.add(song);

        song.put("songTitle", "black ant");
        song.put("songPath", String.valueOf(R.raw.black_ant));

        song.put("songTitle", "box cat");
        song.put("songPath", String.valueOf(R.raw.box_cat));

        song.put("songTitle", "energy");
        song.put("songPath", String.valueOf(R.raw.energy));

        song.put("songTitle", "faithful_dog");
        song.put("songPath", String.valueOf(R.raw.faithful_dog));

        song.put("songTitle", "humsafar");
        song.put("songPath", String.valueOf(R.raw.humsafar));

        song.put("songTitle", "jason_shaw");
        song.put("songPath", String.valueOf(R.raw.jason_shaw));

        song.put("songTitle", "night_owl");
        song.put("songPath", String.valueOf(R.raw.night_owl));

        song.put("songTitle", "romantic");
        song.put("songPath", String.valueOf(R.raw.romantic));

        song.put("songTitle", "siesta");
        song.put("songPath", String.valueOf(R.raw.siesta));

        song.put("songTitle", "siri");
        song.put("songPath", String.valueOf(R.raw.siri));

        song.put("songTitle", "springish");
        song.put("songPath", String.valueOf(R.raw.springish));

        song.put("songTitle", "starling");
        song.put("songPath", String.valueOf(R.raw.starling));

        listAudio.add(song);
        return listAudio;
    }
}
