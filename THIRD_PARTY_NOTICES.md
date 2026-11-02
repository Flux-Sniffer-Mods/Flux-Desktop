# Third-party notices

Flux Desktop is licensed under the GNU General Public License version 3. These parts come from
other projects and keep their own licenses.

## PRoot

- Project: PRoot, as patched for Android by Termux (`termux/proot` at `fa47780ce5576eb22d2f779d700a44640d957529`)
- Source repository: https://github.com/termux/proot (upstream https://github.com/proot-me/proot)
- License: GNU General Public License, version 2 or later
- Source and license text: `third_party/proot/` (`COPYING`); how it's built: `third_party/proot/FLUX.md` and `scripts/build-proot.sh`

Built into `app/src/main/jniLibs/arm64-v8a/libproot.so` and `libproot-loader.so`, to run the Linux desktop without root. Two changes: a missing `#include <string.h>`, and a comment in `src/syscall/enter.c` shortened.

## virglrenderer and libepoxy

- Projects: virglrenderer 1.3.0 (https://gitlab.freedesktop.org/virgl/virglrenderer) and libepoxy 1.5.10 (https://github.com/anholt/libepoxy), with patches from Termux's virglrenderer-android (https://github.com/termux/termux-packages), including texture decoding from gl4es
- License: MIT (each)
- License texts: `third_party/virglrenderer/` (`COPYING`, `COPYING-libepoxy`, `COPYING-gl4es`); how it's built: `third_party/virglrenderer/FLUX.md` and `scripts/build-virgl.sh`

Built into `app/src/main/jniLibs/arm64-v8a/libvirgl.so`, so the Linux desktop's programs can draw on the phone's GPU.

## talloc

- Project: talloc 2.4.2, from the Samba project
- Source: https://www.samba.org/ftp/talloc/talloc-2.4.2.tar.gz
- License: GNU Lesser General Public License, version 3 or later
- Source and license text: `third_party/talloc/`, `third_party/licenses/LGPL-3.0.txt`

Linked into PRoot. `replace.h` is a small stand-in for Samba's lib/replace.

## Debian and Ubuntu

The Linux desktop downloads Debian's official arm64 image (debuerreotype, https://github.com/debuerreotype/docker-debian-artifacts) or Ubuntu's (Ubuntu Base, https://cdimage.ubuntu.com/ubuntu-base/) when it's installed, and its packages from their servers; none of it is in the app. Each package keeps its own license, listed inside Linux under `/usr/share/doc`.

## Klassy and KDE Rounded Corners

- Projects: Klassy, by Paul McAuley and its contributors (https://github.com/paulmcauley/klassy), and KDE Rounded Corners, by Matin Lotfali and its contributors (https://github.com/matinlotfali/KDE-Rounded-Corners)
- Licenses: Klassy, a mix of GPL-2.0-only, GPL-3.0-only, GPL-2.0-or-later, LGPL-2.1-or-later and MIT (its `LICENSES` folder and file headers); KDE Rounded Corners, GPL-3.0

With KDE Plasma on Debian 13 (arm64), the Linux desktop downloads Titan-2-Elite-Debian-Chroot's build of both, made from their unmodified sources for that KWin (`titan-addons-bundle.tar.gz`, whose `THIRD-PARTY.md` says how to rebuild it), and installs it inside Linux; none of it is in the app.

## Titan-2-Elite-Debian-Chroot

- Project: Titan-2-Elite-Debian-Chroot, by Flux-Sniffer-Mods (the same author)
- Source repository: https://github.com/Flux-Sniffer-Mods/Titan-2-Elite-Debian-Chroot
- License: GNU General Public License, version 3 or later

The Linux desktop's chroot mounts, apt and dpkg settings, systemctl stand-in, KDE touch tuning, applications menu, notification bridge, touch panel, window look, Phone widget and the phone's side of it (`app/src/main/assets/linux/`), and its touch gestures and keyboard layers (`TouchGestures.kt`, `KeyLayers.kt`), are adapted from it.
