package com.things.fk.sm;

import com.google.common.base.Preconditions;
import com.things.fk.library.BaseApplication;
import com.things.fk.library.database.Realms;
import com.things.fk.library.http.Retrofits;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * @author tic
 *         created on 18-3-29
 */

public class MainApplication extends BaseApplication {

    private static List<Action> completeActions = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        Flowable.empty()
                .mergeWith(s -> {
                    Retrofits.init(Injection.provideRetrofitsClient(0));
                    Realms.init(this);
                })
                .doOnComplete(() -> {
                    for (Action action : completeActions) {
                        action.run();
                    }
                    completeActions.clear();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    /**
     * addComplete application complete callback
     *
     * @param action
     */
    public static void addComplete(Action action) {
        Preconditions.checkNotNull(action);
        if (completeActions.size() > 3) {
            throw new IllegalArgumentException("To much to do in Application main work");
        }
        completeActions.add(action);
    }

}
