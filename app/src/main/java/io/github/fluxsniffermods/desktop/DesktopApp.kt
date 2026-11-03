package io.github.fluxsniffermods.desktop

import android.app.Application
import io.github.fluxsniffermods.core.FluxCore

class DesktopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FluxCore.init(
            this,
            BuildConfig.VERSION_NAME,
            about = FluxCore.About(
                credits = R.string.desktop_credits,
                // How the desktop is set up, its last start and Linux's own logs
                crashSections = { context ->
                    val report = context.getExternalFilesDir(null)?.let { dir ->
                        runCatching { java.io.File(dir, "desktop.txt").apply { writeText(LinuxDesktop.report(context)) } }.getOrNull()
                    }
                    if (report != null) "echo '== desktop =='; cat '${report.path}'; " else ""
                },
                crashTags = "FluxLinux|FluxExtraScreen|Xvnc|proot"
            )
        )
        // Once: the Linux Flux Titan Hub set up (it lives where the shell keeps it, not in the
        // app) carries on here, its settings taken over from Flux Keyboard
        val app = this
        Thread {
            if (io.github.fluxsniffermods.core.link.FluxMigration.importOnce(app, listOf("linux_"))) LinuxDesktop.refresh(app)
        }.start()
    }
}
