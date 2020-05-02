# GrabT
 CommandLine-clone of Project Grab

The initial GRAB project was meant as a rather advanced USB Grabber - a tool designed to grab as much data off a removable storage device as fast as possible. But unlike other, rather simple scripts, that just copy all the stuff (boooring) GRAB was designed to evaluate each file's specific value beforehand:
Given a drive with 100 Files - instead of 50 mostly boring files we would manage to copy (within half the time necessary to do a full clone as maximum time window), we spend a little time analyzing the data first - and end up with the 49 most interesting files!
There's lots of different aspects to a single file, which indicate it's value to a user. With this project, we aim towards taking all of them into consideration!

Text file, that was never changed after it's creation? Nah, rather boring, probably automatically created....
A file named "Passwords.docx" - definitely want that one!
An archive saying .zip but it's actually a video file, just renamed? Gimme that one!!
Word document, changed recently (months after it's creation), but only 50MB? Could be interesting!

Additionally you are given the opportunity to tweak any of the settings that come with our algorithms, allowing you to exclude certain file types, introduce wanted keywords, or give individual weight to any of the parameters above , like the file's age, size or type.

Unlike it's first version, this project will not come with a proper GUI but relay on CommandLine in- and outputs only.
