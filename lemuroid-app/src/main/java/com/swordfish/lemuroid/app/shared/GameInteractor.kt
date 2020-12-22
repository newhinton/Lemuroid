package com.swordfish.lemuroid.app.shared

import com.swordfish.lemuroid.app.shared.game.GameLauncherActivity
import com.swordfish.lemuroid.app.shared.main.BusyActivity
import com.swordfish.lemuroid.app.mobile.feature.shortcuts.ShortcutsGenerator
import com.swordfish.lemuroid.lib.library.db.RetrogradeDatabase
import com.swordfish.lemuroid.lib.library.db.dao.updateAsync
import com.swordfish.lemuroid.lib.library.db.entity.Game

class GameInteractor(
    private val activity: BusyActivity,
    private val retrogradeDb: RetrogradeDatabase,
    private val useLeanback: Boolean,
    private val shortcutsGenerator: ShortcutsGenerator
) {
    fun onGamePlay(game: Game) {
        // TODO... Display an error message
        if (activity.isBusy()) return
        GameLauncherActivity.launchGame(activity.activity(), game.id, true, useLeanback)
    }

    fun onGameRestart(game: Game) {
        // TODO... Display an error message
        if (activity.isBusy()) return
        GameLauncherActivity.launchGame(activity.activity(), game.id, false, useLeanback)
    }

    fun onFavoriteToggle(game: Game, isFavorite: Boolean) {
        retrogradeDb.gameDao().updateAsync(game.copy(isFavorite = isFavorite)).subscribe()
    }

    fun onCreateShortcut(game: Game) {
        shortcutsGenerator.pinShortcutForGame(game).subscribe()
    }

    fun supportShortcuts(): Boolean {
        return shortcutsGenerator.supportShortcuts()
    }
}
