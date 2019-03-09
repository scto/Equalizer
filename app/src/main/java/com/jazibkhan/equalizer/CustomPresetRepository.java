package com.jazibkhan.equalizer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CustomPresetRepository {
    private CustomPresetDAO mCustomPresetDAO;
    private LiveData<List<CustomPreset>> mAllEntry;
    private LiveData<List<String>> mPresetName;

    CustomPresetRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mCustomPresetDAO = db.entryDAO();
        mAllEntry = mCustomPresetDAO.getAllEntry();
        mPresetName = mCustomPresetDAO.getPresetName();
    }



    LiveData<List<CustomPreset>> getAllEntry() {
        return mAllEntry;
    }

    LiveData<List<String>> getPresetName() {
        return mPresetName;
    }

    public void insert(CustomPreset customPreset) {

        new insertAsyncTask(mCustomPresetDAO).execute(customPreset);
    }

    public void delete(CustomPreset customPreset) {

        new deleteAsyncTask(mCustomPresetDAO).execute(customPreset);
    }

    public void update(CustomPreset customPreset) {

        new updateAsyncTask(mCustomPresetDAO).execute(customPreset);
    }

    private static class updateAsyncTask extends AsyncTask<CustomPreset, Void, Void> {

        private CustomPresetDAO mAsyncTaskDao;

        updateAsyncTask(CustomPresetDAO dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CustomPreset... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }



    private static class insertAsyncTask extends AsyncTask<CustomPreset, Void, Void> {

        private CustomPresetDAO mAsyncTaskDao;

        insertAsyncTask(CustomPresetDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CustomPreset... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<CustomPreset, Void, Void> {

        private CustomPresetDAO mAsyncTaskDao;

        deleteAsyncTask(CustomPresetDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CustomPreset... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
