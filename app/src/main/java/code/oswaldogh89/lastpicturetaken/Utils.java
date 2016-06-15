package code.oswaldogh89.lastpicturetaken;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by oswaldogh89 on 14/06/16.
 */
public class Utils {

    /* numero de imagenes que se muestran */
    public static int CANTIDAD_IMAGENES = 10;

    public static ArrayList<String> getCameraImages(Context context) {
        final String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE
        };
        final Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
        ArrayList<String> result = new ArrayList<>(cursor != null ? cursor.getCount() : 0);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            do {
                final String data = cursor.getString(dataColumn);
                if (result.size() >= CANTIDAD_IMAGENES)
                    break;
                result.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public static ArrayList<Image> getFilePaths(Context ctx) {

        ArrayList<String> latestImages = getCameraImages(ctx);
        ArrayList<Image> resultArray = new ArrayList<>();

        int countImages = 0;
        for (int i = 0; i <= latestImages.size(); i++) {
            try {

                if (latestImages.get(i).toUpperCase().contains(".JPG") ||
                        latestImages.get(i).toUpperCase().contains(".JPGE") ||
                        latestImages.get(i).toUpperCase().contains(".PNG") ||
                        latestImages.get(i).toUpperCase().contains(".GIF") ||
                        latestImages.get(i).toUpperCase().contains(".BMP")) {

                    if (countImages < CANTIDAD_IMAGENES) {
                        String path = latestImages.get(i);
                        File image = new File(path);
                        Image imagen = new Image();
                        imagen.setImgPath(image);
                        resultArray.add(imagen);
                        countImages++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultArray;
    }
}
