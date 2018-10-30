call runCrud.bat

if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo. GRADLEW BUILD encoutered errors - braking program
goto fail

:startchrome
start "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo. could not start chrome
goto fail


:fail
echo.
echo There were errors

:end
echo.
echo work finished
