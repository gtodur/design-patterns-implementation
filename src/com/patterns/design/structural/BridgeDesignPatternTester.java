package com.patterns.design.structural;

//decouple an abstraction from its implementation such that the two can vary independently
//prefer composition over inheritance
//used mainly for implementing platform independence feature
//Publish abstraction interface in separate inheritance hierarchy, and put implementation in its own inheritance hierarchy


public class BridgeDesignPatternTester {

	public static void main(String[] args) {
		String os = "windows";
        FileDownloaderAbstraction downloader = null;
 
        switch (os)
        {
            case "windows":
                downloader = new FileDownloaderAbstractionImpl( new WindowsFileDownloadImplementor() );
                break;
                 
            case "linux":
                downloader = new FileDownloaderAbstractionImpl( new LinuxFileDownloadImplementor() );
                break;
                 
            default:
                System.out.println("OS not supported !!");
        }
         
        Object fileContent = downloader.download("some path");
        downloader.store(fileContent);
	}

}

interface FileDownloaderAbstraction
{
    public Object download(String path);
     
    public boolean store(Object object);
}

class FileDownloaderAbstractionImpl implements FileDownloaderAbstraction {
     
    private FileDownloadImplementor provider = null;
 
    public FileDownloaderAbstractionImpl(FileDownloadImplementor provider) {
        super();
        this.provider = provider;
    }
 
    @Override
    public Object download(String path)
    {
        return provider.downloadFile(path);
    }
 
    @Override
    public boolean store(Object object)
    {
        return provider.storeFile(object);
    }
}

interface FileDownloadImplementor
{
    public Object downloadFile(String path);
     
    public boolean storeFile(Object object);
}

class LinuxFileDownloadImplementor implements FileDownloadImplementor
{
    @Override
    public Object downloadFile(String path) {
        return new Object();
    }
 
    @Override
    public boolean storeFile(Object object) {
        System.out.println("File downloaded successfully in LINUX !!");
        return true;
    }
}

class WindowsFileDownloadImplementor implements FileDownloadImplementor
{
    @Override
    public Object downloadFile(String path) {
        return new Object();
    }
 
    @Override
    public boolean storeFile(Object object) {
        System.out.println("File downloaded successfully in WINDOWS !!");
        return true;
    }
}