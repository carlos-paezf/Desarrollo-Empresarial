                 README: The Java Developers Kit


This 1.0 BETA release of the Java Developers Kit (JDK) lets
you write applets that conform to the 1.0 Java applet API.  

About the Beta JDK
-----------------------------------------

	Welcome to the Beta release of the Java Development Kit for Macintosh.
	The JDK is used specifically to allow people to write Java applets for
	use in a Java aware browser, such as Netscape Navigatorª or Sun's HotJava,
	using the 1.0 Java Applet API.  You can not build standalone
	applications with this version of the JDK.

	The JDK incorporates the Java virtual machine as a shared library,
	along with the AWT (Abstract Window Toolkit), Networking, and
	Multimedia libraries.  (NOTE: the MMedia library is not yet
	available, as of this release..)


About the distribution
-----------------------------------------

	In order to make the release easier to use on the Macintosh, we have used a
	slightly different folder hierarchy than for the other JDK releases. For example,
	a "bin" folder doesn't make much sense on the Mac, so we moved the executable
	applications to the top level folder. Naturally, the content remains the same.


Supported Platforms
-----------------------------------------

	The JDK requires a Macintosh running System 7.5 with 8 megabytes of
	real memory and 7 megabytes of hard disk space.  The JDK is fully
	compatible with virtual memory and most third party extensions.


JDK Contents
-----------------------------------------

	README: This document
	
	COPYRIGHT: You must read this before using the JDK. Please make sure
		you understand the copyright and license information before using
		this release.
	
	Java Compiler: The Java Byte Code Compiler.
    
	AppletViewer: An application for testing and running applets.
	
	Classes: A database of class files used the compiler & applet viewer. 
		This file must be present in the same directory as those
		applications. (NOTE: may change later on!)
	
	Java.shlb: The Java runtime share library for PowerPC.  (NOTE: for
		now, must be in the same directory as the applications, but it
		is not needed for 68k only installations.)
	
	lib: support for the runtime and compiler.  (NOTE: for
		now, must be in the same directory as the applications, and at
		least "javac.properties" must be present for 68k only installations.)
	
	Sample Applets:  An assortment of applets, with source code, for you
		to copy and learn from.  This is the best place to start if you're
		new to applet development.

	API User's Guide.html: Documentation on the Java Class APIs, presented in
		HTML format.
        
	API Documentation: Documentation in .html format.


What is the final applet API?
-----------------------------------------

The final applet API consists of the following packages:  java.lang,
java.util, java.io, java.net, java.awt, java.awt.peer, java.awt.image, 
and java.applet.  We and our partners are committed to supporting this API.

----------------------------------------------------------------------
IMPORTANT: Please make sure you understand the Copyright and License
information (in the file named COPYRIGHT) before using this release.
----------------------------------------------------------------------


Where do I find more information?
-----------------------------------------

	This "README" file and the API documentation is the only documentation
	included in this release.  The rest of the information you need is 
	on our website:
		
        -  http://www.javasoft.com/JDK-1.0/

	It includes the following:
		
        -  Frequently asked questions
		
        -  Changes since the last release
	
        -  Applet examples (some of which are also in this release)

        -  API documentation 

        -  Documentation for the Java programming tools
		
        -  The latest Java Language Specification
		
        -  Known bugs in the JDK 


If you have questions, problems, or comments:
-----------------------------------------

        -  Check out the FAQ at: 
            http://www.javasoft.com/JDK-1.0/faq.html

        -  Check the known bugs at: 
            http://www.javasoft.com/JDK-1.0/KnownBugs-JDK.html

        -  File bug reports at: 
            http://www.javasoft.com/GettingInTouch/BugReport.html

        -  The newsgroup comp.lang.java and the Java/HotJava mailing lists are
            active forums for posting questions and exchanging information  with
            other Java users.  See http://www.javasoft.com/mail.html for  
            information on accessing the newsgroup and mailing lists.

        -  Other questions can be sent to java@java.sun.com. 
		

Java Applications: an overview
-----------------------------------------

	Java applications act similarly to Macintosh applications, in that
	they can have a menu bar, windows, etc.  Most of the differences are
	under the hood, so to speak, but they do show up in the impact that
	a Java application has on your system.  Memory is particularly an
	issue with Java applications.  Java is a garbage collected language,
	which means that the onus of memory management is removed from the
	programmer, and given to the runtime system to handle.  Periodically,
	Java looks at its memory pools and deletes objects that are no longer
	referenced.  Further, if the Java memory allocator cannot satisfy a
	request for memory, it will exhaustively search its heap for objects
	that can be freed.  (When this happens, you may see the bulldozer
	"busy" cursor - please be patient!  Dig we must!)

	There are two pools of memory for a Java application, the Macintosh
	Application Heap, and the Java garbage collected heap.  The
	application heap can be changed by the user via the "Get Info"
	command in the Finder.  The application heap is used entirely for
	typical Macintosh allocations such as windows and menus.  The GC heap
	is allocated in "Multifinder Temporary Memory" which is space that
	is not currently used by other applications.  (If you do "About this
	Macintosh" from the Finder, you'll see a label for "Largest Unused
	Block".  When you launch a Java application, some of this space is
	taken for the app heap, some for the GC heap.)  The GC heap can vary
	in size - the smaller it is, the more time you'll spend waiting for
	the garbage collector to run.  By default, the AppletViewer
	application takes between 300k and 3000k of temp mem, while the
	compiler requests 800k to 6000k.

	Java is a multi-threaded language - this means that while Java code
	is executing (say, updating a graph display), a thread at a higher
	priority (say, a user click) will interrupt that thread and execute
	some other code.  When the second thread completes its task, or
	starts to wait for more input, the first thread will continue where
	it left off.  The Macintosh is inherently not multi-threaded,
	therefore some operations will cause all threads to block, for
	example, when a menu is down.  Other operations, such as selecting
	text in a text field, or clicking in a button, will allow other
	threads to continue.  User input is always given the utmost
	priority.

	Java for Macintosh is a port of a Unix program, and there are still
	some places where the infamous "stderr" and "stdout" windows will
	come to front with a tidbit of trivia - most of the time this
	information isn't important (unless its your own debugging output,
	of course!) and you can safely close the window and move on.  If it
	seems to be reporting an internal error and is persistent, you can
	save the window to a file by hitting "CMD-S" while the window is
	frontmost, and mail it, with a description of how to reproduce the
	problem to "java-bugs@java.sun.com".  We're working to eliminate
	these unnecessary interruptions as much as possible.  You can also
	use the stderr and stdout windows in your own debugging; you can
	even permanantly redirect their output to a file using the "Redirect"
	menu items in the "Exec" menu.


Java Compiler: the Java Bytecode Compiler
-----------------------------------------

	Javac is a standalone Java compiler, with some support for minimal
	user interaction.  It requires that the developer supply his or her
	own editor to actually develop the program.  Some editors that can
	be controlled by AppleEvents are supported directly by the
	compiler.

	List of supported Text Editors:
		
		-  BBEdit 3.5
		-  CodeWarrior IDE 7 or 8
		-  SimpleText

	When the compiler starts up, a status window appears showing the
	current free memory in the Java garbage collected (GC'd) heap, how
	many compiles are happening simultaneously (NOTE: only one allowed
	right now!) and what the compiler is currently doing.

	The compiler has the following menu options:

		-  Open: open a .java file to compile.  If the file compiles
			successfully, the .class files (object files) are placed
			in the directory where the source file was found.

		-  Close: close the frontmost window.  If the window was the
			compiler status window, the compiler exits.

		-  Preferences: set some options for the compiler, such as
			debugging information (NOTE: there is no debugger yet!)
			and preferred editor.
		
		-  Quit: quit the compiler.  This aborts all current compilations.  

	To compile a Java program, you drop the .java file or files onto the
	compiler, or select "Open" from the compilers File menu.  The compiled
	output files are placed in the directory where the sources were
	located.  If an error occurs when compiling, an error reporting
	window will appear with a scrolling list of the text of the error,
	line number, and snippet of code from where the error occurred.  If
	you have the external editor listed in the Preferences dialog, double
	clicking on an error, or clicking the "Edit" button will open the
	file in the editor and select the line where the error occurred, if
	the selected editor supports those events.  If you click the "Rebuild"
	button, the files that you originally tried to compile will be recompiled.

	When the files compile successfully, they will be deposited in the
	current directory as ".class" files.  You don't run applet .class
	files directly; they are read by the AppletViewer program.  Depending
	on how much memory you have, you might want to quit the compiler
	before running the AppletViewer.


AppletViewer: the Java Applet Runner
-----------------------------------------

	AppletViewer is a drag & drop application that parses .html (hypertext
	markup language) files and displays applets declared therein.  You
	use it to view applets that you've developed, or if you don't have
	a web browser, to view applets at other sites.

	When the AppletViewer first starts up, its status window displays the
	current memory available in the Java heap, and the number of applets
	that are currently running.

	The AppletViewer has the following menu options:

		-  Open URL: retrieve a URL and parse its contents, looking
			for APPLET tags.

		-  Open Local: open a local .html file

		-  Properties: configure some applet properties, including byte code
			verification, proxy and firewall servers, and applet access to
			network resources.

		-  Quit: quit the AppletViewer
		
	When an applet is running, it adds an additional "Applets" menu to the menu bar:
	
		-  Restart: reinitialize and rerun the applet.  This doesn't reload the 
       applet from the network or local disk, however.

		-  Reload: reload the applet from whence it came, and restart it.

		-  Clone: make a new copy of the applet window and restart the applet in it. 

		-  Tag: display a window with the APPLET tag that created this applet

		-  Info: show info about the applet.

		-  Edit: 

		-  Properties: same as the main Properties item

	To view an applet, you drop a .html file onto the AppletViewer icon,
	open a local one from the "Open Local" menu item, or specify one via
	a URL via the "Open URL" menu item.  For example, to run the MoleculeViewer
	applet from the JavaSoft website, you would open the Fetch URL window,
	delete the default text, and enter:
	
		http://www.javasoft.com/applets/applets/MoleculeViewer/example3.html


About the APPLET tag
-----------------------------------------

	The APP tag of previous releases is gone, replaced by the APPLET tag. 
	Here is an example of a simple APPLET tag:

		<applet code="MyApplet.class" width=100 height=140></applet>

	This tells the viewer or browser to load the applet whose compiled
	code is in MyApplet.class (in the same directory as the current HTML
	document), and to set the initial size of the applet to 100 pixels
	wide and 140 pixels high.

	Here's a more complex example of an APPLET tag:

		<applet codebase="http://www.javasoft.com/applets/applets/NervousText"
		code="NervousText.class" width=400 height=75 align=center >
		<param name="text" value="This is the Applet Viewer.">
		<blockquote>
		<hr>If you were using a Java-enabled browser, you would see 
		dancing text instead of this paragraph.<hr>
		</blockquote>
		</applet>
		
	This tells the viewer or browser to load the applet whose compiled
	code is at the URL:

		http://www.javasoft.com/applets/applets/NervousText/NervousText.class

	to set the initial size of the applet to 400x75 pixels, and to align
	the applet in the center of the line.  The viewer/browser must also
	set the applet's "text" attribute (which customizes the text this
	applet displays) to be "This is the Applet Viewer."  If the page is
	viewed by a browser that can't execute Java applets, then the browser
	will ignore the APPLET and PARAM tags, displaying the HTML between
	the <blockquote> and </blockquote> tags.  Java-enabled browsers
	ignore that HTML.

	Here's the complete syntax for the APPLET tag:

		'<' 'APPLET'
		['CODEBASE' '=' codebaseURL]
		'CODE' '=' appletFile
		['ALT' '=' alternateText]
		['NAME' '=' appletInstanceName]
		'WIDTH' '=' pixels 'HEIGHT' '=' pixels
		['ALIGN' '=' alignment]
		['VSPACE' '=' pixels] ['HSPACE' '=' pixels]
		'>'
		['<' 'PARAM' 'NAME' '=' appletAttribute1 'VALUE' '=' value '>']
		['<' 'PARAM' 'NAME' '=' appletAttribute2 'VALUE' '=' value '>']
		...
		[alternateHTML]
		'</APPLET>'

	-  'CODEBASE' '=' codebaseURL
	
		This optional attribute specifies the base URL of the appletÑthe
		directory that contains the applet's code.  If this attribute is not
		specified, then the document's URL is used.

	-  'CODE' '=' appletFile
	
		This required attribute gives the name of the file that contains the
		applet's compiled Applet subclass.  This file is relative to the base
		URL of the applet.  It cannot be absolute.

	-  'ALT' '=' alternateText
	
		This optional attribute specifies any text that should bedisplayed
		if the browser understands the APPLET tag but can't run Java
		applets.

	-  'NAME' '=' appletInstanceName

		This optional attribute specifies a name for the applet instance,
		which makes it possible for applets on the same page to find (and
		communicate with) each other.

	-  'WIDTH' '=' pixels 'HEIGHT' '=' pixels

		These required attributes give the initial width and height (in
		pixels) of the applet display area, not counting any windows or
		dialogs that the applet brings up.

	-  'ALIGN' '=' alignment
	
		This required attribute specifies the alignment of the applet. The
		possible values of this attribute are the same as those for the IMG
		tag: left, right, top, texttop, middle, absmiddle, baseline, bottom,
		absbottom.

	-  'VSPACE' '=' pixels 'HSPACE' '=' pixels

		These option attributes specify the number of pixels above and below
		the applet (VSPACE) and on each side of the applet (HSPACE). They're
		treated the same way as the IMG tag's VSPACE and HSPACE attributes.

	-  '<' 'PARAM' 'NAME' '=' appletAttribute1 'VALUE' '=' value '>' . . .

		This tag is the only way to specify an applet-specific attribute.
		Applets access their attributes with the getParameter() method.
