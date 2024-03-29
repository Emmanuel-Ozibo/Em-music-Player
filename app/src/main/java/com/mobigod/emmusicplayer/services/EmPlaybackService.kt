package com.mobigod.emmusicplayer.services

import android.media.browse.MediaBrowser
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.mobigod.emmusicplayer.player.EMPlayerSessionCallback

//import android.support.v4.media.session.MediaSessionCompat

class EmPlaybackService: MediaBrowserServiceCompat() {

    private var mMediaSession: MediaSessionCompat? = null
    lateinit var playBackState: PlaybackStateCompat.Builder
    private val MEDIA_TAG = "MediaBrowserService"
    private val MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id"


    override fun onCreate() {
        super.onCreate()


        mMediaSession = MediaSessionCompat(this, MEDIA_TAG).apply {

            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
                    or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
            )

            playBackState = PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE)


            setPlaybackState(playBackState.build())

            setCallback(EMPlayerSessionCallback(this@EmPlaybackService)
                .apply {
                    ms = mMediaSession
                })

            setSessionToken(sessionToken)
        }
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {

    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        return BrowserRoot(MY_EMPTY_MEDIA_ROOT_ID, null)
    }

}