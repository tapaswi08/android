package com.example.googlemap
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val  channelId = "notification_channel"
const val channelName = "com.example.googlemap"
class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.e("Tag","Notification Payload ${remoteMessage.data}")
       // if(remoteMessage.getNotification() != null){
        //    notificationapp(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
       // }
    }


    fun getRemoteView(title: String, message: String): RemoteViews{
        val remoteView = RemoteViews("com.example.googlemap",R.layout.notification)
        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.message,message)
        remoteView.setImageViewResource(R.id.app_logo,R.drawable.mapimage)

        return remoteView
    }

    fun notificationapp(title: String, message: String){
        val intent = Intent(this,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingintent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder= NotificationCompat.Builder(applicationContext,channelId)
            .setSmallIcon(R.drawable.mapimage)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingintent)
        builder= builder.setContent(getRemoteView(title,message))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(System.currentTimeMillis().toInt(),builder.build())


    }
}
