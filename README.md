# Flux Desktop

**A Linux desktop on your phone, or on a TV.** Debian or Ubuntu, with Xfce or KDE Plasma, made for
touch and fitted to the phone's screen (its camera cutout and rounded corners included). With root
it runs as a chroot; without, through PRoot. No Termux needed: Flux Desktop runs it through its
built-in shell, paired once with wireless debugging (or through another Flux app's, already paired).

Part of the Flux apps for the Unihertz Titan 2 Elite: [Flux Keyboard](https://github.com/Flux-Sniffer-Mods/Flux-Keyboard),
[Flux Gaming](https://github.com/Flux-Sniffer-Mods/Flux-Gaming), [Flux Streaming](https://github.com/Flux-Sniffer-Mods/Flux-Streaming)
and Flux Desktop. Each is an app of its own; installed together they work as one.

## What it does

- **Installs Linux in a tap**: Debian 13 or Ubuntu 26.04, Xfce or KDE Plasma (switched later without reinstalling).
- **Made for touch**: natural scrolling with fling, long-press for a right click, two fingers to scroll, the keyboard's Alt and Sym layers as Flux Keyboard has them.
- **KDE Plasma fitted to the phone**: a panel that hides, a launcher of big icons, touch-sized title bars and rounded windows (Klassy and KDE Rounded Corners on Debian), and the Phone widget for the battery, Wi-Fi, signal, brightness, volume and the phone's switches.
- **The phone's GPU** for Linux programs, through virglrenderer.
- **On a TV**: on Miracast's screen, or Flux Streaming's screen of its own through Moonlight.
- **Programs on the home screen**: pin any Linux program as a shortcut.

## Get it

Download the APK from [Releases](https://github.com/Flux-Sniffer-Mods/Flux-Desktop/releases). It
updates itself from there (Dev builds on its About page offers each dev build too).

## Building

```
git clone --recurse-submodules https://github.com/Flux-Sniffer-Mods/Flux-Desktop
./gradlew :app:assembleDebug
```

`core/` is [Flux Core](https://github.com/Flux-Sniffer-Mods/Flux-Core), what the Flux apps share.
PRoot and the GPU server are prebuilt in `app/src/main/jniLibs/` (`scripts/build-proot.sh`,
`scripts/build-virgl.sh` build them again).

## Credits and license

GPL-3.0. Third-party parts and their licenses: [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
What changed: [CHANGES.md](CHANGES.md).
