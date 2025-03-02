$scriptDir = @ScriptDir
$fileName = "fzMedIPPSTesting.xml"
$dynamicFilePath = $scriptDir & "\" & $fileName
ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1",$dynamicFilePath)
ControlClick("Open","","Button2")