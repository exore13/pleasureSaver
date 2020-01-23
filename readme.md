# Pleasure Saver

A zero struggle way to get high quality MP3 files from across the Internet

- [Description](#Description)
- [Instructions](#Instructions)
- [Requirements](#Requirements)


**Latest Stable Release:** [`v0.2j`](https://github.com/exore13/pleasureSaver/raw/master/builds/v0.2j/v0.2j.7z)

**Support:**  _dtanase22@gmail.com_


## Description

_PleasureSaver_ is a Windows Only command-line program that uses youtube-dl to download the highest posible audio source from the provided url, and then convert it to MP3 format using ffmpeg converter. 
The downloaded audio file will be located on `psDownloads/` and you will found the converted ones on `psConverted/`.
The output text of youtube-dl and ffmpeg can be found on the `logs/` folder.

*Supported Sites:* [ytdl-org.github.io/youtube-dl/supportedsites](http://ytdl-org.github.io/youtube-dl/supportedsites.html)

## Instructions

- Open a terminal. `cmd` and `powershell` work fine

```
java -jar .\psProject.jar
```

- Introduce the URLs to download
- Write `Q` or `q` to start the conversion and quit

## Requirements
- Java 8: [java.com/en/download](https://java.com/en/download/)
- Microsoft VC Redist x86 2010: [microsoft.com/en-us/download/details.aspx?id=5555](https://www.microsoft.com/en-us/download/details.aspx?id=5555)

```
 .d8888b.                         888 888888b.            d8b 
d88P  Y88b                        888 888  "88b           Y8P 
888    888                        888 888  .88P               
888         .d88b.   .d88b.   .d88888 8888888K.   .d88b.  888 
888  88888 d88""88b d88""88b d88" 888 888  "Y88b d88""88b 888 
888    888 888  888 888  888 888  888 888    888 888  888 888 
Y88b  d88P Y88..88P Y88..88P Y88b 888 888   d88P Y88..88P 888 
 "Y8888P88  "Y88P"   "Y88P"   "Y88888 8888888P"   "Y88P"  888 
```
