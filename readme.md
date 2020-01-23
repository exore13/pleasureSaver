# Pleasure Saver

A zero struggle way to get high quality MP3 files from across the Internet

- [Description](#Description)
- [Instructions](#Instructions)
- [Requirements](#Requirements)



**Latest Stable Release:** [`v0.1j`](https://github.com/exore13/pleasureSaver/raw/master/builds/v0.1j/Release%20v0.1j.7z)

**Support:**  _dtanase22@gmail.com_


## Description

_PleasureSaver_ is a command-line program that uses youtube-dl to download the highest posible audio source from the provided url, and then convert it to MP3 format using ffmpeg converter. 
The downloaded audio file will be located on `psDownloads/` and you will found the converted ones on `psConverted/`.
The output text of youtube-dl and ffmpeg can be found on the `logs/` folder.

*Supported Sites:* [ytdl-org.github.io/youtube-dl/supportedsites](http://ytdl-org.github.io/youtube-dl/supportedsites.html)

## Instructions

- Open a terminal. `cmd` and `powershell` work fine

```
java -jar .\pleasureSaver.jar
```

- Introduce the URLs to download
- Write `Q` or `q` to start the conversion and quit

## Requirements
- Java 8: [java.com/en/download](https://java.com/en/download/)
- Microsoft VC Redist x86 2010: [microsoft.com/en-us/download/details.aspx?id=5555](https://www.microsoft.com/en-us/download/details.aspx?id=5555)
