package classes;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.PointerByReference;
 
public class ForegroundWinTool
{
     private static final int MAX_TITLE_LENGTH = 1024;
 
     public static String getActiveWindowTitle()
     {
          char[] buffer = new char[MAX_TITLE_LENGTH * 2];
          HWND foregroundWindow = User32DLL.GetForegroundWindow();
          User32DLL.GetWindowTextW(foregroundWindow, buffer, MAX_TITLE_LENGTH);
          String title = Native.toString(buffer);
          return title;
     }
 
     public static String getActiveWindowProcess()
     {
          char[] buffer = new char[MAX_TITLE_LENGTH * 2];
          PointerByReference pointer = new PointerByReference();
          HWND foregroundWindow = User32DLL.GetForegroundWindow();
          User32DLL.GetWindowThreadProcessId(foregroundWindow, pointer);
          Pointer process = Kernel32.OpenProcess(Kernel32.PROCESS_QUERY_INFORMATION | Kernel32.PROCESS_VM_READ, false, pointer.getValue());
          Psapi.GetModuleBaseNameW(process, null, buffer, MAX_TITLE_LENGTH);
          String processName = Native.toString(buffer);
          return processName;
     }
 
     static class Psapi
     {
          static
          {
               Native.register("psapi");
          }
 
          public static native int GetModuleBaseNameW(Pointer hProcess, Pointer hmodule, char[] lpBaseName, int size);
   }
 
     static class Kernel32
     {
          static
          {
               Native.register("kernel32");
          }
 
          public static int PROCESS_QUERY_INFORMATION = 0x0400;
          public static int PROCESS_VM_READ = 0x0010;
 
          public static native Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer pointer);
     }
 
     static class User32DLL
     {
          static
          {
               Native.register("user32");
          }
 
          public static native int GetWindowThreadProcessId(HWND hWnd, PointerByReference pref);
          public static native HWND GetForegroundWindow();
          public static native int GetWindowTextW(HWND hWnd, char[] lpString, int nMaxCount);
     }
}