definition(
    name: "BigTalker-DEV-Child",
    namespace: "rayzurbock",
    author: "rayzur@rayzurbock.com",
    description: "Dependency for BigTalker-DEV-Parent -- Save but don't install/publish",
    category: "Fun & Social",
    parent: "rayzurbock:BigTalker-DEV-Parent",
    iconUrl: "http://lowrance.cc/ST/icons/BigTalker-AlphaVersion.png",
    iconX2Url: "http://lowrance.cc/ST/icons/BigTalker@2x-AlphaVersion.png",
    iconX3Url: "http://lowrance.cc/ST/icons/BigTalker@2x-AlphaVersion.png")

preferences {
    page(name: "pageConfigureEvents")
    page(name: "pageConfigMotion")
    page(name: "pageConfigSwitch")
    page(name: "pageConfigPresence")
    page(name: "pageConfigLock")
    page(name: "pageConfigContact")
    page(name: "pageConfigMode")
    page(name: "pageConfigThermostat")
    page(name: "pageConfigAcceleration")
    page(name: "pageConfigWater")
    page(name: "pageConfigSmoke")
    page(name: "pageConfigButton")
    page(name: "pageConfigTime")
    page(name: "pageConfigSHM")
    page(name: "pageHelpPhraseTokens")
}

def pageConfigureEvents(){
    dynamicPage(name: "pageConfigureEvents", title: "Configure Events", install: (!(app?.getInstallationState == true)), uninstall: (app?.getInstallationState == true)) {
        section("Talk on events:") {
        	label(name: "labelRequired", title: "Event Group Name:", defaultValue: "Change this", required: true, multiple: false)
            if (settings.timeSlotTime1 || settings.timeSlotTime2 || settings.timeSlotTime3) {
                href "pageConfigTime", title: "Time", description: "Tap to modify"
            } else {
                href "pageConfigTime", title: "Time", description: "Tap to configure"
            }
            if (settings.motionDeviceGroup1 || settings.motionDeviceGroup2 || settings.motionDeviceGroup3) {
                href "pageConfigMotion", title:"Motion", description:"Tap to modify"
            } else {
                href "pageConfigMotion", title:"Motion", description:"Tap to configure"
            }
            if (settings.switchDeviceGroup1 || settings.switchDeviceGroup2 || settings.switchDeviceGroup3) {
                href "pageConfigSwitch", title:"Switch", description:"Tap to modify"
            } else {
                href "pageConfigSwitch", title:"Switch", description:"Tap to configure"
            }
            if (settings.presDeviceGroup1 || settings.presDeviceGroup2 || settings.presDeviceGroup3) {
                href "pageConfigPresence", title:"Presence", description:"Tap to modify"
            } else {
                href "pageConfigPresence", title:"Presence", description:"Tap to configure"
            }
            if (settings.lockDeviceGroup1 || settings.lockDeviceGroup2 || settings.lockDeviceGroup3) {
                href "pageConfigLock", title:"Lock", description:"Tap to modify"
            } else {
                href "pageConfigLock", title:"Lock", description:"Tap to configure"
            }
            if (settings.contactDeviceGroup1 || settings.contactDeviceGroup2 || settings.contactDeviceGroup3) {
                href "pageConfigContact", title:"Contact", description:"Tap to modify"
            } else {
                href "pageConfigContact", title:"Contact", description:"Tap to configure"
            }
            if (settings.modePhraseGroup1 || settings.modePhraseGroup2 || settings.modePhraseGroup3) {
                href "pageConfigMode", title:"Mode", description:"Tap to modify"
            } else {
                href "pageConfigMode", title:"Mode", description:"Tap to configure"
            }
            if (settings.thermostatDeviceGroup1 || settings.thermostatDeviceGroup2 || settings.thermostatDeviceGroup3) {
                href "pageConfigThermostat", title:"Thermostat", description:"Tap to modify"
            } else {
                href "pageConfigThermostat", title:"Thermostat", description:"Tap to configure"
            }
            if (settings.accelerationDeviceGroup1 || settings.accelerationDeviceGroup2 || settings.accelerationDeviceGroup3) {
                href "pageConfigAcceleration", title: "Acceleration", description:"Tap to modify"
            } else {
                href "pageConfigAcceleration", title: "Acceleration", description:"Tap to configure"
            }
            if (settings.waterDeviceGroup1 || settings.waterDeviceGroup2 || settings.waterDeviceGroup3) {
                href "pageConfigWater", title: "Water", description:"Tap to modify"
            } else {
                href "pageConfigWater", title: "Water", description:"Tap to configure"
            }
            if (settings.smokeDeviceGroup1 || settings.smokeDeviceGroup2 || settings.smokeDeviceGroup3) {
                href "pageConfigSmoke", title: "Smoke", description:"Tap to modify"
            } else { 
                href "pageConfigSmoke", title: "Smoke", description:"Tap to configure"
            }
            if (settings.buttonDeviceGroup1 || settings.buttonDeviceGroup2 || settings.buttonDeviceGroup3) {
                href "pageConfigButton", title: "Button", description:"Tap to configure"
            } else {
                href "pageConfigButton", title: "Button", description:"Tap to configure"
            }
            if (settings.SHMDeviceGroup1) {
                href "pageConfigSHM", title: "Smart Home Monitor", description:"Tap to configure"
            } else {
                href "pageConfigSHM", title: "Smart Home Monitor", description:"Tap to configure"
            }
        }
    }
}

def pageConfigMotion(){
    dynamicPage(name: "pageConfigMotion", title: "Configure talk on motion", install: false, uninstall: false) {
        section("Motion Sensor Group 1"){
            def defaultSpeechActive1 = ""
            def defaultSpeechInactive1 = ""
            if (!motionDeviceGroup1) {
                defaultSpeechActive1 = "%devicename% is now %devicechange%"
                defaultSpeechInactive1 = "%devicename% is now %devicechange%"
            }
            input name: "motionDeviceGroup1", type: "capability.motionSensor", title: "Motion Sensor(s)", required: false, multiple: true
            input name: "motionTalkActive1", type: "text", title: "Say this on motion active:", required: false, defaultValue: defaultSpeechActive1
            input name: "motionTalkInactive1", type: "text", title: "Say this on motion inactive:", required: false, defaultValue: defaultSpeechInactive1
            input name: "motionSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "motionResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "motionModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "motionStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "motionEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.motionStartTime1 == null))
        }
        section("Motion Sensor Group 2"){
            input name: "motionDeviceGroup2", type: "capability.motionSensor", title: "Motion Sensor(s)", required: false, multiple: true
            input name: "motionTalkActive2", type: "text", title: "Say this on motion active:", required: false
            input name: "motionTalkInactive2", type: "text", title: "Say this on motion inactive:", required: false
            input name: "motionSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "motionResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "motionModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "motionStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "motionEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.motionStartTime2 == null))
        }
        section("Motion Sensor Group 3"){
            input name: "motionDeviceGroup3", type: "capability.motionSensor", title: "Motion Sensor(s)", required: false, multiple: true
            input name: "motionTalkActive3", type: "text", title: "Say this on motion active:", required: false
            input name: "motionTalkInactive3", type: "text", title: "Say this on motion inactive:", required: false
            input name: "motionSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "motionResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "motionModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "motionStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "motionEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.motionStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigMotion()
}

def pageConfigSwitch(){
    dynamicPage(name: "pageConfigSwitch", title: "Configure talk on switch", install: false, uninstall: false) {
        section("Switch Group 1"){
            def defaultSpeechOn1 = ""
            def defaultSpeechOff1 = ""
            if (!switchDeviceGroup1) {
                defaultSpeechOn1 = "%devicename% is now %devicechange%"
                defaultSpeechOff1 = "%devicename% is now %devicechange%"
            }
            input name: "switchDeviceGroup1", type: "capability.switch", title: "Switch(es)", required: false, multiple: true
            input name: "switchTalkOn1", type: "text", title: "Say this when switch is turned ON:", required: false, defaultValue: defaultSpeechOn1
            input name: "switchTalkOff1", type: "text", title: "Say this when switch is turned OFF:", required: false, defaultValue: defaultSpeechOff1
            input name: "switchSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "switchResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "switchModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "switchStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "switchEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.switchStartTime1 == null))
        }
        section("Switch Group 2"){
            input name: "switchDeviceGroup2", type: "capability.switch", title: "Switch(es)", required: false, multiple: true
            input name: "switchTalkOn2", type: "text", title: "Say this when switch is turned ON:", required: false
            input name: "switchTalkOff2", type: "text", title: "Say this when switch is turned OFF:", required: false
            input name: "switchSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "switchResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "switchModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "switchStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "switchEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.switchStartTime2 == null))
        }
        section("Switch Group 3"){
            input name: "switchDeviceGroup3", type: "capability.switch", title: "Switch(es)", required: false, multiple: true
            input name: "switchTalkOn3", type: "text", title: "Say this when switch is turned ON:", required: false
            input name: "switchTalkOff3", type: "text", title: "Say this when switch is turned OFF:", required: false
            input name: "switchSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "switchResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "switchModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "switchStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "switchEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.switchStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigSwitch()
}

def pageConfigPresence(){
    dynamicPage(name: "pageConfigPresence", title: "Configure talk on presence", install: false, uninstall: false) {
        section("Presence Group 1"){
            def defaultSpeechArrive1 = ""
            def defaultSpeechLeave1 = ""
            if (!presDeviceGroup1) {
                defaultSpeechArrive1 = "%devicename% has arrived"
                defaultSpeechLeave1 = "%devicename% has left"
            }
            input name: "presDeviceGroup1", type: "capability.presenceSensor", title: "Presence Sensor(s)", required: false, multiple: true
            input name: "presTalkOnArrive1", type: "text", title: "Say this when someone arrives:", required: false, defaultValue: defaultSpeechArrive1
            input name: "presTalkOnLeave1", type: "text", title: "Say this when someone leaves:", required: false, defaultValue: defaultSpeechLeave1
            input name: "presSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "presResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "presModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "presStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "presEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.presStartTime1 == null))
        }
        section("Presence Group 2"){
            input name: "presDeviceGroup2", type: "capability.presenceSensor", title: "Presence Sensor(s)", required: false, multiple: true
            input name: "presTalkOnArrive2", type: "text", title: "Say this when someone arrives:", required: false
            input name: "presTalkOnLeave2", type: "text", title: "Say this when someone leaves:", required: false
            input name: "presSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "presResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "presModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "presStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "presEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.presStartTime2 == null))
        }
        section("Presence Group 3"){
            input name: "presDeviceGroup3", type: "capability.presenceSensor", title: "Presence Sensor(s)", required: false, multiple: true
            input name: "presTalkOnArrive3", type: "text", title: "Say this when someone arrives:", required: false
            input name: "presTalkOnLeave3", type: "text", title: "Say this when someone leaves:", required: false
            input name: "presSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "presResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "presModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "presStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "presEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.presStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigPresence()
}

def pageConfigLock(){
    dynamicPage(name: "pageConfigLock", title: "Configure talk on lock", install: false, uninstall: false) {
        section("Lock Group 1"){
            def defaultSpeechUnlock1 = ""
            def defaultSpeechLock1 = ""
            if (!lockDeviceGroup1) {
                defaultSpeechUnlock1 = "%devicename% is now %devicechange%"
                defaultSpeechLock1 = "%devicename% is now %devicechange%"
            }
            input name: "lockDeviceGroup1", type: "capability.lock", title: "Lock(s)", required: false, multiple: true
            input name: "lockTalkOnUnlock1", type: "text", title: "Say this when unlocked:", required: false, defaultValue: defaultSpeechUnlock1
            input name: "lockTalkOnLock1", type: "text", title: "Say this when locked:", required: false, defaultValue: defaultSpeechLock1
            input name: "lockSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "lockResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "lockModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "lockStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "lockEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.lockStartTime1 == null))
        }
        section("Lock Group 2"){
            input name: "lockDeviceGroup2", type: "capability.lock", title: "Lock(s)", required: false, multiple: true
            input name: "lockTalkOnUnlock2", type: "text", title: "Say this when unlocked:", required: false
            input name: "lockTalkOnLock2", type: "text", title: "Say this when locked:", required: false
            input name: "lockSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "lockResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "lockModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "lockStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "lockEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.lockStartTime2 == null))
        }
        section("Lock Group 3"){
            input name: "lockDeviceGroup3", type: "capability.lock", title: "Lock(s)", required: false, multiple: true
            input name: "lockTalkOnUnlock3", type: "text", title: "Say this when unlocked:", required: false
            input name: "lockTalkOnLock3", type: "text", title: "Say this when locked:", required: false
            input name: "lockSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "lockResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "lockModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "lockStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "lockEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.lockStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigLock()
}

def pageConfigContact(){
    dynamicPage(name: "pageConfigContact", title: "Configure talk on contact sensor", install: false, uninstall: false) {
        section("Contact Group 1"){
            def defaultSpeechOpen1 = ""
            def defaultSpeechClose1 = ""
            if (!contactDeviceGroup1) {
                defaultSpeechOpen1 = "%devicename% is now %devicechange%"
                defaultSpeechClose1 = "%devicename% is now %devicechange%"
            }
            input name: "contactDeviceGroup1", type: "capability.contactSensor", title: "Contact sensor(s)", required: false, multiple: true
            input name: "contactTalkOnOpen1", type: "text", title: "Say this when opened:", required: false, defaultValue: defaultSpeechOpen1
            input name: "contactTalkOnClose1", type: "text", title: "Say this when closed:", required: false, defaultValue: defaultSpeechClose1
            input name: "contactSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "contactResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "contactModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "contactStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "contactEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.contactStartTime1 == null))
        }
        section("Contact Group 2"){
            input name: "contactDeviceGroup2", type: "capability.contactSensor", title: "Contact sensor(s)", required: false, multiple: true
            input name: "contactTalkOnOpen2", type: "text", title: "Say this when opened:", required: false
            input name: "contactTalkOnClose2", type: "text", title: "Say this when closed:", required: false
            input name: "contactSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "contactResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "contactModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "contactStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "contactEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.contactStartTime2 == null))
        }
        section("Contact Group 3"){
            input name: "contactDeviceGroup3", type: "capability.contactSensor", title: "Contact sensor(s)", required: false, multiple: true
            input name: "contactTalkOnOpen3", type: "text", title: "Say this when opened:", required: false
            input name: "contactTalkOnClose3", type: "text", title: "Say this when closed:", required: false
            input name: "contactSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "contactResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "contactModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "contactStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "contactEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.contactStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigContact()
}

def pageConfigMode(){
    def locationmodes = []
    location.modes.each(){
       locationmodes += it
    }
    LOGDEBUG("locationmodes=${locationmodes}")
    dynamicPage(name: "pageConfigMode", title: "Configure talk on home mode change", install: false, uninstall: false) {
        section("Mode Group 1"){
            def defaultSpeechMode1 = ""
            if (!modePhraseGroup1) {
                defaultSpeechMode1 = "%locationname% mode has changed from %lastmode% to %mode%"
            }
            input name: "modePhraseGroup1", type:"mode", title:"When mode changes to: ", required:false, multiple:true, submitOnChange:false
            input name: "modeExcludePhraseGroup1", type: "mode", title: "But not when changed from (optional): ", required: false, multiple: true
            input name: "TalkOnModeChange1", type: "text", title: "Say this when home mode is changed", required: false, defaultValue: defaultSpeechMode1
            input name: "modePhraseSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "modePhraseResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "modeStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "modeEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.modeStartTime1 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigMode()
}

def pageConfigThermostat(){
    dynamicPage(name: "pageConfigThermostat", title: "Configure talk when thermostat state is:", install: false, uninstall: false) {
        section("Thermostat Group 1"){
            def defaultSpeechIdle1 = ""
            def defaultSpeechHeating1 = ""
            def defaultSpeechCooling1 = ""
            def defaultSpeechFan1 = ""
            if (!thermostatDeviceGroup1) {
                defaultSpeechIdle1 = "%devicename% is now off"
                defaultSpeechHeating1 = "%devicename% is now heating"
                defaultSpeechCooling1 = "%devicename% is now cooling"
                defaultSpeechFan1 = "%devicename% is now circulating fan"
            }
            input name: "thermostatDeviceGroup1", type: "capability.thermostat", title: "Thermostat(s)", required: false, multiple: true
            input name: "thermostatTalkOnIdle1", type: "text", title: "Say this on change to Idle:", required: false, defaultValue: defaultSpeechIdle1
            input name: "thermostatTalkOnHeating1", type: "text", title: "Say this on change to heating:", required: false, defaultValue: defaultSpeechHeating1
            input name: "thermostatTalkOnCooling1", type: "text", title: "Say this on change to cooling:", required: false, defaultValue: defaultSpeechCooling1
            input name: "thermostatTalkOnFan1", type: "text", title: "Say this on change to fan only:", required: false, defaultValue: defaultSpeechFan1
            input name: "thermostatSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "thermostateResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "thermostatModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "thermostatStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "thermostatEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.thermostatStartTime1 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigThermostat()
}

def pageConfigAcceleration(){
    dynamicPage(name: "pageConfigAcceleration", title: "Configure talk on acceleration", install: false, uninstall: false) {
        section("Acceleration Group 1"){
            def defaultSpeechActive1 = ""
            def defaultSpeechInactive1 = ""
            if (!accelerationDeviceGroup1) {
                defaultSpeechActive1 = "%devicename% acceleration %devicechange%"
                defaultSpeechInactive1 = "%devicename% acceleration is no longer active"
            }
            input name: "accelerationDeviceGroup1", type: "capability.accelerationSensor", title: "Acceleration sensor(s)", required: false, multiple: true
            input name: "accelerationTalkOnActive1", type: "text", title: "Say this when activated:", required: false, defaultValue: defaultSpeechActive1
            input name: "accelerationTalkOnInactive1", type: "text", title: "Say this when inactivated:", required: false, defaultValue: defaultSpeechInactive1
            input name: "accelerationSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "accelerationResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "accelerationModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "accelerationStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "accelerationEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.accelerationStartTime1 == null))
        }
        section("Acceleration Group 2"){
            input name: "accelerationDeviceGroup2", type: "capability.accelerationSensor", title: "Acceleration sensor(s)", required: false, multiple: true
            input name: "accelerationTalkOnActive2", type: "text", title: "Say this when activated:", required: false
            input name: "accelerationTalkOnInactive2", type: "text", title: "Say this when inactivated:", required: false
            input name: "accelerationSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "accelerationResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "accelerationModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "accelerationStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "accelerationEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.accelerationStartTime2 == null))
        }
        section("Acceleration Group 3"){
            input name: "accelerationDeviceGroup3", type: "capability.accelerationSensor", title: "Acceleration sensor(s)", required: false, multiple: true
            input name: "accelerationTalkOnActive3", type: "text", title: "Say this when activated:", required: false
            input name: "accelerationTalkOnInactive3", type: "text", title: "Say this when inactivated:", required: false
            input name: "accelerationSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "accelerationResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "accelerationModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "accelerationStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "accelerationEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.accelerationStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigAcceleration()
}

def pageConfigWater(){
    dynamicPage(name: "pageConfigWater", title: "Configure talk on water", install: false, uninstall: false) {
        section("Water Group 1"){
            def defaultSpeechWet1 = ""
            def defaultSpeechDry1 = ""
            if (!waterDeviceGroup1) {
                defaultSpeechWet1 = "%devicename% is %devicechange%"
                defaultSpeechDry1 = "%devicename% is %devicechange%"
            }
            input name: "waterDeviceGroup1", type: "capability.waterSensor", title: "Water sensor(s)", required: false, multiple: true
            input name: "waterTalkOnWet1", type: "text", title: "Say this when wet:", required: false, defaultValue: defaultSpeechWet1
            input name: "waterTalkOnDry1", type: "text", title: "Say this when dry:", required: false, defaultValue: defaultSpeechDry1
            input name: "waterSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "waterResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "waterModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "waterStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "waterEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.waterStartTime1 == null))
        }
        section("Water Group 2"){
            input name: "waterDeviceGroup2", type: "capability.waterSensor", title: "Water sensor(s)", required: false, multiple: true
            input name: "waterTalkOnWet2", type: "text", title: "Say this when wet:", required: false
            input name: "waterTalkOnDry2", type: "text", title: "Say this when dry:", required: false
            input name: "waterSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "waterResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "waterModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "waterStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "waterEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.waterStartTime2 == null))
        }
        section("Water Group 3"){
            input name: "waterDeviceGroup3", type: "capability.waterSensor", title: "Water sensor(s)", required: false, multiple: true
            input name: "waterTalkOnWet3", type: "text", title: "Say this when wet:", required: false
            input name: "waterTalkOnDry3", type: "text", title: "Say this when dry:", required: false
            input name: "waterSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "waterResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "waterModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "waterStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "waterEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.waterStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigWater()
}

def pageConfigSmoke(){
    dynamicPage(name: "pageConfigSmoke", title: "Configure talk on smoke", install: false, uninstall: false) {
        section("Smoke Group 1"){
            def defaultSpeechDetect1 = ""
            def defaultSpeechClear1 = ""
            def defaultSpeechTest1 = ""
            if (!smokeDeviceGroup1) {
                defaultSpeechDetect1 = "Smoke, %devicename% has detected smoke"
                defaultSpeechClear1 = "Smoke, %devicename% has cleared smoke alert"
                defaultSpeechTest1 = "Smoke, %devicename% has been tested"
            }
            input name: "smokeDeviceGroup1", type: "capability.smokeDetector", title: "Smoke detector(s)", required: false, multiple: true
            input name: "smokeTalkOnDetect1", type: "text", title: "Say this when detected:", required: false, defaultValue: defaultSpeechDetect1
            input name: "smokeTalkOnClear1", type: "text", title: "Say this when cleared:", required: false, defaultValue: defaultSpeechClear1
            input name: "smokeTalkOnTest1", type: "text", title: "Say this when tested:", required: false, defaultValue: defaultSpeechTest1
            input name: "smokeSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "smokeResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "smokeModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "smokeStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "smokeEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.smokeStartTime1 == null))
        }
        section("Smoke Group 2"){
            input name: "smokeDeviceGroup2", type: "capability.smokeDetector", title: "Smoke detector(s)", required: false, multiple: true
            input name: "smokeTalkOnDetect2", type: "text", title: "Say this when detected:", required: false
            input name: "smokeTalkOnClear2", type: "text", title: "Say this when cleared:", required: false
            input name: "smokeTalkOnTest2", type: "text", title: "Say this when tested:", required: false
            input name: "smokeSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "smokeResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "smokeModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "smokeStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "smokeEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.smokeStartTime2 == null))
        }
        section("Smoke Group 3"){
            input name: "smokeDeviceGroup3", type: "capability.smokeDetector", title: "Smoke detector(s)", required: false, multiple: true
            input name: "smokeTalkOnDetect3", type: "text", title: "Say this when detected:", required: false
            input name: "smokeTalkOnClear3", type: "text", title: "Say this when cleared:", required: false
            input name: "smokeTalkOnTest3", type: "text", title: "Say this when tested:", required: false
            input name: "smokeSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "smokeResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "smokeModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "smokeStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "smokeEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.smokeStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigSmoke()
}

def pageConfigButton(){
    dynamicPage(name: "pageConfigButton", title: "Configure talk on button press", install: false, uninstall: false) {
        section("Button Group 1"){
            def defaultSpeechButton1 = ""
            def defaultSpeechButtonHold1 = ""
            if (!buttonDeviceGroup1) {
                defaultSpeechButton1 = "%devicename% button pressed"
                defaultSpeechButtonHold1 = "%devicename% button held"
            }
            input name: "buttonDeviceGroup1", type: "capability.button", title: "Button(s)", required: false, multiple: true
            input name: "buttonTalkOnPress1", type: "text", title: "Say this when pressed:", required: false, defaultValue: defaultSpeechButton1
            input name: "buttonTalkOnHold1", type: "text", title: "Say this when held:", required: false, defaultValue: defaultSpeechButtonHold1
            input name: "buttonSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "buttonResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "buttonModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "buttonStartTime1", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "buttonEndTime1", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.buttonStartTime1 == null))
        }
        section("Button Group 2"){
            input name: "buttonDeviceGroup2", type: "capability.button", title: "Button(s)", required: false, multiple: true
            input name: "buttonTalkOnPress2", type: "text", title: "Say this when pressed:", required: false
            input name: "buttonTalkOnHold2", type: "text", title: "Say this when held:", required: false
            input name: "buttonSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "buttonResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "buttonModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "buttonStartTime2", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "buttonEndTime2", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.buttonStartTime2 == null))
        }
        section("Button Group 3"){
            input name: "buttonDeviceGroup3", type: "capability.button", title: "Button(s)", required: false, multiple: true
            input name: "buttonTalkOnPress3", type: "text", title: "Say this when pressed:", required: false
            input name: "buttonTalkOnHold3", type: "text", title: "Say this when held:", required: false
            input name: "buttonSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "buttonResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "buttonModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "buttonStartTime3", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "buttonEndTime3", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.buttonStartTime3 == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigButton()
}

def pageConfigSHM(){
    dynamicPage(name: "pageConfigSHM", title: "Configure talk on Smart Home Monitor status change", install: false, uninstall: false) {
        section("Smart Home Monitor - Armed, Away"){
            def defaultSpeechSHMAway = ""
            if (settings.SHMTalkOnAway == null) {
                defaultSpeechSHMAway = "Smart Home Monitor is now Armed in Away mode"
            }
            input name: "SHMTalkOnAway", type: "text", title: "Say this when Armed, Away:", required: false, defaultValue: defaultSpeechSHMAway
            input name: "SHMSpeechDeviceAway", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "SHMResumePlayAway", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "SHMModesAway", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "SHMStartTimeAway", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "SHMEndTimeAway", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.SHMStartTimeAway == null))
        }
        section("Smart Home Monitor - Armed, Home"){
        	def defaultSpeechSHMHome = ""
            if (settings.SHMTalkOnHome == null) {
                defaultSpeechSHMHome = "Smart Home Monitor is now Armed in Home mode"
            }
            input name: "SHMTalkOnHome", type: "text", title: "Say this when Armed, Home:", required: false, defaultValue: defaultSpeechSHMHome
            input name: "SHMSpeechDeviceHome", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "SHMResumePlayHome", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "SHMModesHome", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "SHMStartTimeHome", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "SHMEndTimeHome", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.SHMStartTimeHome == null))
        }
        section("Smart Home Monitor - Disarmed"){
        	def defaultSpeechSHMDisarm = ""
            if (settings.SHMTalkOnDisarm == null) {
                defaultSpeechSHMDisarm = "Smart Home Monitor is now Disarmed"
            }
            input name: "SHMTalkOnDisarm", type: "text", title: "Say this when Disarmed:", required: false, defaultValue: defaultSpeechSHMDisarm
            input name: "SHMSpeechDeviceDisarm", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "SHMResumePlayDisarm", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "SHMModesDisarm", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
            input name: "SHMStartTimeDisarm", type: "time", title: "Don't talk before (overrides default)", required: false, submitOnChange: true
            input name: "SHMEndTimeDisarm", type: "time", title: "Don't talk after (overrides default)", required: (!(settings.SHMStartTimeDisarm == null))
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
//End pageConfigSHM()
}

def pageConfigTime(){
    dynamicPage(name: "pageConfigTime", title: "Configure talk at specific time(s)", install: false, uninstall: false) {
        section("Time Slot 1"){
            input name: "timeSlotDays1", type: "enum", title: "Which day(s)", required: false, options: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], multiple: true
            input name: "timeSlotTime1", type: "time", title: "Time of day", required: false
            input name: "timeSlotOnTime1", type: "text", title: "Say on schedule:", required: false
            input name: "timeSlotSpeechDevice1", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "timeSlotResumePlay1", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "timeSlotModes1", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
        }
        section("Time Slot 2"){
            input name: "timeSlotDays2", type: "enum", title: "Which day(s)", required: false, options: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], multiple: true
            input name: "timeSlotTime2", type: "time", title: "Time of day", required: false
            input name: "timeSlotOnTime2", type: "text", title: "Say on schedule:", required: false
            input name: "timeSlotSpeechDevice2", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "timeSlotResumePlay2", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "timeSlotModes2", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
        }
        section("Time Slot 3"){
            input name: "timeSlotDays3", type: "enum", title: "Which day(s)", required: false, options: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], multiple: true
            input name: "timeSlotTime3", type: "time", title: "Time of day", required: false
            input name: "timeSlotOnTime3", type: "text", title: "Say on schedule:", required: false
            input name: "timeSlotSpeechDevice3", type: parent.state.speechDeviceType, title: "Talk with these text-to-speech devices (overrides default)", multiple: true, required: false
            if (parent.state.speechDeviceType == "capability.musicPlayer") {
            	input name: "timeSlotResumePlay3", type: "bool", title: "Attempt to resume playing audio?", required: false, defaultValue: (parent?.settings?.resumePlay == false) ? false : true
            }
            input name: "timeSlotModes3", type: "mode", title: "Talk when in these mode(s) (overrides default)", multiple: true, required: false
        }
        section("Help"){
            href "pageHelpPhraseTokens", title:"Phrase Tokens", description:"Tap for a list of phrase tokens"
        }
    }
}

def pageHelpPhraseTokens(){
	//KEEP IN SYNC WITH PARENT!
    dynamicPage(name: "pageHelpPhraseTokens", title: "Available Phrase Tokens", install: false, uninstall:false){
       section("The following tokens can be used in your event phrases and will be replaced as listed:"){
       	   def AvailTokens = ""
           AvailTokens += "%askalexa% = Send phrase to AskAlexa SmartApp's message queue\n\n"
           AvailTokens += "%devicename% = Triggering devices display name\n\n"
           AvailTokens += "%devicetype% = Triggering device type; motion, switch, etc\n\n"
           AvailTokens += "%devicechange% = State change that occurred; on/off, active/inactive, etc...\n\n"
           AvailTokens += "%description% = The description of the event that is to be displayed to the user in the mobile application. \n\n"
           AvailTokens += "%locationname% = Hub location name; home, work, etc\n\n"
           AvailTokens += "%lastmode% = Last hub mode; home, away, etc\n\n"
           AvailTokens += "%mode% = Current hub mode; home, away, etc\n\n"
           AvailTokens += "%time% = Current hub time; HH:mm am/pm\n\n"
           AvailTokens += "%shmstatus% = SmartHome Monitor Status (Disarmed, Armed Home, Armed Away)\n\n"
           AvailTokens += "%weathercurrent% = Current weather based on hub location\n\n"
           AvailTokens += "%weathercurrent(00000)% = Current weather* based on custom zipcode (replace 00000)\n\n"
           AvailTokens += "%weathertoday% = Today's weather forecast* based on hub location\n\n"
           AvailTokens += "%weathertoday(00000)% = Today's weather forecast* based on custom zipcode (replace 00000)\n\n"
           AvailTokens += "%weathertonight% = Tonight's weather forecast* based on hub location\n\n"
           AvailTokens += "%weathertonight(00000)% = Tonight's weather* forecast based on custom zipcode (replace 00000)\n\n"
           AvailTokens += "%weathertomorrow% = Tomorrow's weather forecast* based on hub location\n\n"
           AvailTokens += "%weathertomorrow(00000)% = Tomorrow's weather forecast* based on custom zipcode (replace 00000)\n\n"
           AvailTokens += "\n*Weather forecasts provided by Weather Underground"
           paragraph(AvailTokens)
       }
   }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

def initialize() {
	setAppVersion()
    initSchedule()
    initSubscribe()
    LOGTRACE("Initialized (Parent Version: ${parent.state.appversion}; Child Version: ${state.appversion})")
    sendNotificationEvent("${app.label.replace(" ","").toUpperCase()}: Settings activated")
    state.lastMode = location.mode
//End initialize()
}
def updated() {
	setAppVersion()
    unschedule()
    state.installed = true
	//LOGTRACE("Updated with settings: ${settings}")
    LOGTRACE("Updated settings (Parent Version: ${parent.state.appversion}; Child Version: ${state.appversion})")
    unsubscribe()
    initialize()
}
def installed() {
	LOGTRACE("Installed")
}

def initSubscribe(){
    //NOTICE: Only call from initialize()!
    LOGDEBUG ("BEGIN initSubscribe()")
    //Subscribe Motions
    if (motionDeviceGroup1) { subscribe(motionDeviceGroup1, "motion", onMotion1Event) }
    if (motionDeviceGroup2) { subscribe(motionDeviceGroup2, "motion", onMotion2Event) }
    if (motionDeviceGroup3) { subscribe(motionDeviceGroup3, "motion", onMotion3Event) }
    //Subscribe Switches
    if (switchDeviceGroup1) { subscribe(switchDeviceGroup1, "switch", onSwitch1Event) }
    if (switchDeviceGroup2) { subscribe(switchDeviceGroup2, "switch", onSwitch2Event) }
    if (switchDeviceGroup3) { subscribe(switchDeviceGroup3, "switch", onSwitch3Event) }
    //Subscribe Presence
    if (presDeviceGroup1) { subscribe(presDeviceGroup1, "presence", onPresence1Event) }
    if (presDeviceGroup2) { subscribe(presDeviceGroup2, "presence", onPresence2Event) }
    if (presDeviceGroup3) { subscribe(presDeviceGroup3, "presence", onPresence3Event) }
    //Subscribe Lock
    if (lockDeviceGroup1) { subscribe(lockDeviceGroup1, "lock", onLock1Event) }
    if (lockDeviceGroup2) { subscribe(lockDeviceGroup2, "lock", onLock2Event) }
    if (lockDeviceGroup3) { subscribe(lockDeviceGroup3, "lock", onLock3Event) }
    //Subscribe Contact
    if (contactDeviceGroup1) { subscribe(contactDeviceGroup1, "contact", onContact1Event) }
    if (contactDeviceGroup2) { subscribe(contactDeviceGroup2, "contact", onContact2Event) }
    if (contactDeviceGroup3) { subscribe(contactDeviceGroup3, "contact", onContact3Event) }
    def contact1StartTime = settings.contact1StartTime ?: null
    //Subscribe Thermostat
    if (thermostatDeviceGroup1) { subscribe(thermostatDeviceGroup1, "thermostatOperatingState", onThermostat1Event) }
    if (thermostatDeviceGroup2) { subscribe(thermostatDeviceGroup2, "thermostatOperatingState", onThermostat2Event) }
    if (thermostatDeviceGroup3) { subscribe(thermostatDeviceGroup3, "thermostatOperatingState", onThermostat3Event) }
    //Subscribe Acceleration
    if (accelerationDeviceGroup1) { subscribe(accelerationDeviceGroup1, "acceleration", onAcceleration1Event) }
    if (accelerationDeviceGroup2) { subscribe(accelerationDeviceGroup2, "acceleration", onAcceleration2Event) }
    if (accelerationDeviceGroup3) { subscribe(accelerationDeviceGroup3, "acceleration", onAcceleration3Event) }
    //Subscribe Water
    if (waterDeviceGroup1) { subscribe(waterDeviceGroup1, "water", onWater1Event) }
    if (waterDeviceGroup2) { subscribe(waterDeviceGroup2, "water", onWater2Event) }
    if (waterDeviceGroup3) { subscribe(waterDeviceGroup3, "water", onWater3Event) }
    //Subscribe Smoke
    if (smokeDeviceGroup1) { subscribe(smokeDeviceGroup1, "smoke", onSmoke1Event) }
    if (smokeDeviceGroup2) { subscribe(smokeDeviceGroup2, "smoke", onSmoke2Event) }
    if (smokeDeviceGroup3) { subscribe(smokeDeviceGroup3, "smoke", onSmoke3Event) }
    //Subscribe Button
    if (buttonDeviceGroup1) { subscribe(buttonDeviceGroup1, "button", onButton1Event) }
    if (buttonDeviceGroup2) { subscribe(buttonDeviceGroup2, "button", onButton2Event) }
    if (buttonDeviceGroup3) { subscribe(buttonDeviceGroup3, "button", onButton3Event) }
    //Subscribe SHM
    if (SHMTalkOnAway || SHMTalkOnHome || SHMTalkOnDisarm) { subscribe(location, "alarmSystemStatus", onSHMEvent) }
    //Subscribe Mode
    if (modePhraseGroup1) { subscribe(location, onModeChangeEvent) }
    
    LOGDEBUG ("END initSubscribe()")
}

def initSchedule(){
    LOGDEBUG ("BEGIN initSchedule()")
    //Subscribe Schedule
    if (timeSlotTime1) { schedule(timeSlotTime1, onSchedule1Event) }
    if (timeSlotTime2) { schedule(timeSlotTime2, onSchedule2Event) }
    if (timeSlotTime3) { schedule(timeSlotTime3, onSchedule3Event) }
    LOGDEBUG ("END initSchedule()")
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
def timeAllowed(devicetype,index){
    def now = new Date()
    //Check Default Setting
    //devicetype = mode, motion, switch, presence, lock, contact, thermostat, acceleration, water, smoke, button
    switch (devicetype) {
        case "mode":
            if (index == 1 && (!(settings.modeStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.modeStartTime1, settings.modeEndTime1, now, location.timeZone)) { return true } else { return false }
            }
        case "motion":
            if (index == 1 && (!(settings.motionStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.motionStartTime1, settings.motionEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.motionStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.motionStartTime2, settings.motionEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.motionStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.motionStartTime3, settings.motionEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "switch":
            if (index == 1 && (!(settings.switchStartTime1 == null))) {
                    if (timeOfDayIsBetween(settings.switchStartTime1, settings.switchEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.switchStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.switchStartTime2, settings.switchEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.switchStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.switchStartTime3, settings.switchEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "presence":
            if (index == 1 && (!(settings.presenceStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.presenceStartTime1, settings.presenceEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.presenceStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.presenceStartTime2, settings.presenceEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.presenceStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.presenceStartTime3, settings.presenceEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "lock":
            if (index == 1 && (!(settings.lockStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.lockStartTime1, settings.lockEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.lockStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.lockStartTime2, settings.lockEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.lockStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.lockStartTime3, settings.lockEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "contact":
            if (index == 1 && (!(settings.contactStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.contactStartTime1, settings.contactEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.contactStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.contactStartTime2, settings.contactEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.contactStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.contactStartTime3, settings.contactEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "thermostat":
            if (index == 1 && (!(settings.thermostatStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.thermostatStartTime1, settings.thermostatEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.thermostatStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.thermostatStartTime2, settings.thermostatEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.thermostatStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.thermostatStartTime3, settings.thermostatEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "acceleration":
            if (index == 1 && (!(settings.accelerationStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.accelerationStartTime1, settings.accelerationEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.accelerationStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.accelerationStartTime2, settings.accelerationEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.accelerationStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.accelerationStartTime3, settings.accelerationEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "water":
            if (index == 1 && (!(settings.waterStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.waterStartTime1, settings.waterEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.waterStartTime2 == null))) {
                    if (timeOfDayIsBetween(settings.waterStartTime2, settings.waterEndTime2, now, location.timeZone)) { return true } else { return false }
                }
            if (index == 3 && (!(settings.waterStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.waterStartTime3, settings.waterEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "smoke":
            if (index == 1 && (!(settings.smokeStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.smokeStartTime1, settings.smokeEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.smokeStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.smokeStartTime2, settings.smokeEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.smokeStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.smokeStartTime3, settings.smokeEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "button":
            if (index == 1 && (!(settings.buttonStartTime1 == null))) {
                if (timeOfDayIsBetween(settings.buttonStartTime1, settings.buttonEndTime1, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.buttonStartTime2 == null))) {
                if (timeOfDayIsBetween(settings.buttonStartTime2, settings.buttonEndTime2, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.buttonStartTime3 == null))) {
                if (timeOfDayIsBetween(settings.buttonStartTime3, settings.buttonEndTime3, now, location.timeZone)) { return true } else { return false }
            }
        case "SHM":
            if (index == 1 && (!(settings.SHMStartTimeAway == null))) {
                if (timeOfDayIsBetween(settings.SHMStartTimeAway, settings.SHMEndTimeAway, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 2 && (!(settings.SHMStartTimeHome == null))) {
                if (timeOfDayIsBetween(settings.SHMStartTimeHome, settings.SHMEndTimeHome, now, location.timeZone)) { return true } else { return false }
            }
            if (index == 3 && (!(settings.SHMStartTimeDisarm == null))) {
                if (timeOfDayIsBetween(settings.SHMStartTimeDisarm, settings.SHMEndTimeDisarm, now, location.timeZone)) { return true } else { return false }
            }
    }
    
    //No overrides have returned True, process Default
    if (parent?.settings?.defaultStartTime == null) { 
    	return true 
    } else {
        if (timeOfDayIsBetween(parent?.settings?.defaultStartTime, parent?.settings?.defaultEndTime, now, location.timeZone)) { return true } else { return false }
    }
}

def modeAllowed(devicetype,index) {
    //Determine if we are allowed to speak in our current mode based on the calling device or default setting
    //devicetype = motion, switch, presence, lock, contact, thermostat, acceleration, water, smoke, button
    switch (devicetype) {
        case "motion":
            if (index == 1) {
                //Motion Group 1
                if (settings.motionModes1) {
                    if (settings.motionModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Motion Group 2
                if (settings.motionModes2) {
                    if (settings.motionModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Motion Group 3
                if (settings.motionModes3) {
                    if (settings.motionModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "motion"
        case "switch":
            if (index == 1) {
                //Switch Group 1
                if (settings.switchModes1) {
                    if (settings.switchModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Switch Group 2
                if (settings.switchModes2) {
                    if (settings.switchModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Switch Group 3
                if (settings.switchModes3) {
                    if (settings.switchModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "switch"
        case "presence":
            if (index == 1) {
                //Presence Group 1
                if (settings.presenceModes1) {
                    if (settings.presenceModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Presence Group 2
                if (settings.presenceModes2) {
                    if (settings.presenceModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Presence Group 3
                if (settings.presenceModes3) {
                    if (settings.presenceModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "presence"
        case "lock":
            if (index == 1) {
                //Lock Group 1
                if (settings.lockModes1) {
                    if (settings.lockModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Lock Group 2
                if (settings.lockModes2) {
                    if (settings.lockModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Lock Group 3
                if (settings.lockModes3) {
                    if (settings.lockModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "lock"
        case "contact":
            if (index == 1) {
                //Contact Group 1
                if (settings.contactModes1) {
                    if (settings.contactModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Contact Group 2
                if (settings.contactModes2) {
                    if (settings.contactModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Contact Group 3
                if (settings.contactModes3) {
                    if (settings.contactModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "contact"
        case "thermostat":
            if (index == 1) {
                //Thermostat Group 1
                if (settings.thermostatModes1) {
                    if (settings.thermostatModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Thermostat Group 2
                if (settings.thermostatModes2) {
                    if (settings.thermostatModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Thermostat Group 3
                if (settings.thermostatModes3) {
                    if (settings.thermostatModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "thermostat"
        case "acceleration":
            if (index == 1) {
                //Acceleration Group 1
                if (settings.accelerationModes1) {
                    if (settings.accelerationModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Acceleration Group 2
                if (settings.accelerationModes2) {
                    if (settings.accelerationModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Acceleration Group 3
                if (settings.accelerationModes3) {
                    if (settings.accelerationModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "acceleration"
        case "water":
            if (index == 1) {
                //Water Group 1
                if (settings.waterModes1) {
                    if (settings.waterModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Water Group 2
                if (settings.waterModes2) {
                    if (settings.waterModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Water Group 3
                if (settings.waterModes3) {
                    if (settings.waterModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "water"
        case "smoke":
            if (index == 1) {
                //Smoke Group 1
                if (settings.smokeModes1) {
                    if (settings.smokeModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Smoke Group 2
                if (settings.smokeModes2) {
                    if (settings.smokeModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Smoke Group 3
                if (settings.smokeModes3) {
                    if (settings.smokeModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "smoke"
        case "button":
            if (index == 1) {
                //Button Group 1
                if (settings.buttonModes1) {
                    if (settings.buttonModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //Button Group 2
                if (settings.buttonModes2) {
                    if (settings.buttonModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //Button Group 3
                if (settings.buttonModes3) {
                    if (settings.buttonModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "button"
        case "SHM":
            if (index == 1) {
                //SHM Armed Away
                if (settings.SHMModesAway) {
                    if (settings.SHMModesAway.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //SHM Armed Home
                if (settings.SHMModesHome) {
                    if (settings.SHMModesHome.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //SHM Disarmed
                if (settings.SHMModesDisarm) {
                    if (settings.SHMModesDisarm.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "SHM"
        case "timeSlot":
            if (index == 1) {
                //TimeSlot Group 1
                if (settings.timeSlotModes1) {
                    if (settings.timeSlotModes1.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 2) {
                //TimeSlot Group 2
                if (settings.timeSlotModes2) {
                    if (settings.timeSlotModes2.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
            if (index == 3) {
                //TimeSlot Group 3
                if (settings.timeSlotModes3) {
                    if (settings.timeSlotModes3.contains(location.mode)) {
                        //Custom mode for this event is in use and we are in one of those modes
                        return true
                    } else {
                        //Custom mode for this event is in use and we are not in one of those modes
                        return false
                    }
                } else {
                    return (parent?.settings?.speechModesDefault.contains(location.mode)) //True if we are in an allowed Default mode, False if not
                }
            }
        //End: case "timeSlot"
    } //End: switch (devicetype)
}

def getTimeFromDateString(inputtime, includeAmPm){
    //I couldn't find the way to do this in ST / Groovy, so I made my own function
    //Obtains the time from a supplied specifically formatted date string (ie: from a preference of type "time")
    //LOGDEBUG "InputTime: ${inputtime}"
    def outputtime = inputtime
    def am_pm = "??"
    outputtime = inputtime.substring(11,16)
    if (includeAmPm) {
        if ((outputtime.substring(0,2)).toInteger() < 12) { 
            am_pm = "am" 
        } else { 
            am_pm = "pm"
            def newHH = ((outputtime.substring(0,2)).toInteger() - 12)
            outputtime = newHH + outputtime.substring(2,5)
        }
        outputtime += am_pm
    }
    //LOGDEBUG "OutputTime: ${outputtime}"
    return outputtime
}

def getTimeFromCalendar(includeSeconds, includeAmPm){
    //Obtains the current time:  HH:mm:ss am/pm
    def calendar = Calendar.getInstance()
	calendar.setTimeZone(location.timeZone)
	def timeHH = calendar.get(Calendar.HOUR)
    def timemm = calendar.get(Calendar.MINUTE)
    def timess = calendar.get(Calendar.SECOND)
    def timeampm = calendar.get(Calendar.AM_PM) ? "pm" : "am"
    def timestring = "${timeHH}:${timemm}"
    if (includeSeconds) { timestring += ":${timess}" }
    if (includeAmPm) { timestring += " ${timeampm}" }
    return timestring
}

//myRunIn from ST:Geko / Statusbits SmartAlarm app http://statusbits.github.io/smartalarm/
private def myRunIn(delay_s, func) {
    //LOGDEBUG("myRunIn(${delay_s},${func})")

    if (delay_s > 0) {
        def tms = now() + (delay_s * 1000)
        def date = new Date(tms)
        runOnce(date, func)
        //LOGDEBUG("'${func}' scheduled to run at ${date}")
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// HANDLE EVENTS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//BEGIN HANDLE TIME SCHEDULE
def onSchedule1Event(){
    processScheduledEvent(1, timeSlotTime1, timeSlotDays1)
}
def onSchedule2Event(){
    processScheduledEvent(2, timeSlotTime2, timeSlotDays2)
}
def onSchedule3Event(){
    processScheduledEvent(3, timeSlotTime3, timeSlotDays3)
}

def processScheduledEvent(index, eventtime, alloweddays){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    def calendar = Calendar.getInstance()
	calendar.setTimeZone(location.timeZone)
	def today = calendar.get(Calendar.DAY_OF_WEEK)
    def todayStr = ""
    def dayMatch = false
    switch (today) {
        case Calendar.MONDAY: todayStr = "MONDAY"; break
		case Calendar.TUESDAY:  todayStr = "TUESDAY"; break
		case Calendar.WEDNESDAY:  todayStr = "WEDNESDAY"; break
		case Calendar.THURSDAY:  todayStr = "THURSDAY"; break
        case Calendar.FRIDAY:  todayStr = "FRIDAY"; break
        case Calendar.SATURDAY:  todayStr = "SATURDAY"; break
        case Calendar.SUNDAY:  todayStr = "SUNDAY"; break
    }
    //LOGDEBUG("today=${today}, MON=${Calendar.MONDAY},TUE=${Calendar.TUESDAY},WED=${Calendar.WEDNESDAY},THUR=${Calendar.THURSDAY},FRI=${Calendar.FRIDAY},SAT=${Calendar.SATURDAY},SUN=${Calendar.SUNDAY}")
    def timeNow = getTimeFromDateString(eventtime, true)
    LOGDEBUG("(onScheduledEvent): ${timeNow}, ${index}, ${todayStr.toUpperCase()}, ${alloweddays.each(){return it.toUpperCase()}}")
    alloweddays.each(){
        if (todayStr.toUpperCase() == it.toUpperCase()) {
            LOGDEBUG("Time and day match schedule")
            dayMatch = true
            if (!(modeAllowed("timeSlot",index))) { 
               LOGDEBUG("Remain silent while in mode ${location.mode}")
               return
            }
			if (parent.state.speechDeviceType == "capability.musicPlayer") {
				if (index == 1) {
					if (!settings?.timeSlotResumePlay1 == null) { resume = settings.timeSlotResumePlay1 }
				}
				if (index == 2) {
					if (!settings?.timeSlotResumePlay2 == null) { resume = settings.timeSlotResumePlay2 }
				}
				if (index == 3) {
					if (!settings?.timeSlotResumePlay3 == null) { resume = settings.timeSlotResumePlay3 }
				}
                if (resume == null) { resume = true }
			} else { resume = false }
            if (index == 1) { state.TalkPhrase = settings.timeSlotOnTime1; state.speechDevice = timeSlotSpeechDevice1}
            if (index == 2) { state.TalkPhrase = settings.timeSlotOnTime2; state.speechDevice = timeSlotSpeechDevice2}
            if (index == 3) { state.TalkPhrase = settings.timeSlotOnTime3; state.speechDevice = timeSlotSpeechDevice3}
            def customevent = [displayName: 'BigTalker:OnSchedule', name: 'OnSchedule', value: "${todayStr}@${timeNow}"]
            parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, customevent)
        }
    }
    if (!dayMatch) { 
    	LOGDEBUG("Time matches, but day does not match schedule; remaining silent") 
    }
    state.TalkPhrase = null
    state.speechDevice = null
}

//BEGIN HANDLE MOTIONS
def onMotion1Event(evt){
    processMotionEvent(1, evt)
}
def onMotion2Event(evt){
    processMotionEvent(2, evt)
}
def onMotion3Event(evt){
    processMotionEvent(3, evt)
}

def processMotionEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onMotionEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("motion",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("motion",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.motionResumePlay1 == null) { resume = settings.motionResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.motionResumePlay2 == null) { resume = settings.motionResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.motionResumePlay3 == null) { resume = settings.motionResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "active") {
        if (index == 1) { state.TalkPhrase = settings.motionTalkActive1; state.speechDevice = motionSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.motionTalkActive2; state.speechDevice = motionSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.motionTalkActive3; state.speechDevice = motionSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "inactive") {
        if (index == 1) { state.TalkPhrase = settings.motionTalkInactive1; state.speechDevice = motionSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.motionTalkInactive2; state.speechDevice = motionSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.motionTalkInactive3; state.speechDevice = motionSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE MOTIONS
//BEGIN HANDLE SWITCHES
def onSwitch1Event(evt){
    processSwitchEvent(1, evt)
}

def onSwitch2Event(evt){
    processSwitchEvent(2, evt)
}

def onSwitch3Event(evt){
    processSwitchEvent(3, evt)
}

def processSwitchEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onSwitchEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("switch",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("switch",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!(settings?.switchResumePlay1 == null)) { resume = settings.switchResumePlay1 }
		}
		if (index == 2) {
			if (!(settings?.switchResumePlay2 == null)) { resume = settings.switchResumePlay2 }
		}
		if (index == 3) {
			if (!(settings?.switchResumePlay3 == null)) { resume = settings.switchResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "on") {
        if (index == 1) { state.TalkPhrase = settings.switchTalkOn1; state.speechDevice = switchSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.switchTalkOn2; state.speechDevice = switchSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.switchTalkOn3; state.speechDevice = switchSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "off") {
        if (index == 1) { state.TalkPhrase = settings.switchTalkOff1; state.speechDevice = switchSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.switchTalkOff2; state.speechDevice = switchSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.switchTalkOff3; state.speechDevice = switchSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE SWITCHES
//BEGIN HANDLE PRESENCE
def onPresence1Event(evt){
    processPresenceEvent(1, evt)
}
def onPresence2Event(evt){
    processPresenceEvent(2, evt)
}
def onPresence3Event(evt){
    processPresenceEvent(3, evt)
}

def processPresenceEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onPresenceEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("presence",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("presence",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.presResumePlay1 == null) { resume = settings.presResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.presResumePlay2 == null) { resume = settings.presResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.presResumePlay3 == null) { resume = settings.presResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "present") {
        if (index == 1) { state.TalkPhrase = settings.presTalkOnArrive1; state.speechDevice = presSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.presTalkOnArrive2; state.speechDevice = presSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.presTalkOnArrive3; state.speechDevice = presSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "not present") {
        if (index == 1) { state.TalkPhrase = settings.presTalkOnLeave1; state.speechDevice = presSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.presTalkOnLeave2; state.speechDevice = presSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.presTalkOnLeave3; state.speechDevice = presSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE PRESENCE

//BEGIN HANDLE LOCK
def onLock1Event(evt){
    LOGDEBUG("onLock1Event(evt) ${evt.value}")
    processLockEvent(1, evt)
}
def onLock2Event(evt){
    processLockEvent(2, evt)
}
def onLockEvent(evt){
    processLockEvent(3, evt)
}

def processLockEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onLockEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("lock",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("lock",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.lockResumePlay1 == null) { resume = settings.lockResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.lockResumePlay2 == null) { resume = settings.lockResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.lockResumePlay3 == null) { resume = settings.lockResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "locked") {
        if (index == 1) { state.TalkPhrase = settings.lockTalkOnLock1; state.speechDevice = lockSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.lockTalkOnLock2; state.speechDevice = lockSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.lockTalkOnLock3; state.speechDevice = lockSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "unlocked") {
        if (index == 1) { state.TalkPhrase = settings.lockTalkOnUnlock1; state.speechDevice = lockSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.lockTalkOnUnlock2; state.speechDevice = lockSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.lockTalkOnUnlock3; state.speechDevice = lockSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE LOCK

//BEGIN HANDLE CONTACT
def onContact1Event(evt){
    processContactEvent(1, evt)
}
def onContact2Event(evt){
    processContactEvent(2, evt)
}
def onContactEvent(evt){
    processContactEvent(3, evt)
}

def processContactEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onContactEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("contact",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("contact",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.contactResumePlay1 == null) { resume = settings.contactResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.contactResumePlay2 == null) { resume = settings.contactResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.contactResumePlay3 == null) { resume = settings.contactResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "open") {
        if (index == 1) { state.TalkPhrase = settings.contactTalkOnOpen1; state.speechDevice = contactSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.contactTalkOnOpen2; state.speechDevice = contactSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.contactTalkOnOpen3; state.speechDevice = contactSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "closed") {
        if (index == 1) { state.TalkPhrase = settings.contactTalkOnClose1; state.speechDevice = contactSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.contactTalkOnClose2; state.speechDevice = contactSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.contactTalkOnClose3; state.speechDevice = contactSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE CONTACT

//BEGIN MODE CHANGE
def onModeChangeEvent(evt){
    processModeChangeEvent(1, evt)
}
def processModeChangeEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onModeEvent): Last Mode: ${state.lastMode}, New Mode: ${location.mode}")
    //Are we in an allowed time period?
    if (!(timeAllowed("mode",index))) {
        LOGDEBUG("Remain silent in current time period")
        state.lastMode = location.mode
        return
    }
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.modePhraseResumePlay1 == null) { resume = settings.modePhraseResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.modePhraseResumePlay2 == null) { resume = settings.modePhraseResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.modePhraseResumePlay3 == null) { resume = settings.modePhraseResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (settings.modePhraseGroup1.contains(location.mode)){
        if (!settings.modeExcludePhraseGroup1 == null){
            //settings.modeExcluePhraseGroup1 is not empty
            if (!(settings.modeExcludePhraseGroup1.contains(state.lastMode))) {
                //If we are not coming from an exclude mode, Talk.
                state.TalkPhrase = null
                state.speechDevice = null
                state.TalkPhrase = settings.TalkOnModeChange1; state.speechDevice = modePhraseSpeechDevice1
                if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
                state.TalkPhrase = null
                state.speechDevice = null
            } else {
                LOGDEBUG("Mode change silent due to exclusion configuration (${state.lastMode} >> ${location.mode})")
            }
        } else {
            //settings.modeExcluePhraseGroup1 is empty, no exclusions, Talk.
            state.TalkPhrase = null
            state.speechDevice = null
            state.TalkPhrase = settings.TalkOnModeChange1; state.speechDevice = modePhraseSpeechDevice1
            if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
            state.TalkPhrase = null
            state.speechDevice = null
        }
    }
    state.lastMode = location.mode
}
//END MODE CHANGE

//BEGIN HANDLE THERMOSTAT
def onThermostat1Event(evt){
    processThermostatEvent(1, evt)
}
def onThermostat2Event(evt){
    processThermostatEvent(2, evt)
}
def onThermostatEvent(evt){
    processThermostatEvent(3, evt)
}

def processThermostatEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onThermostatEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("thermostat",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("thermostat",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.thermostatResumePlay1 == null) { resume = settings.thermostatResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.thermostatResumePlay2 == null) { resume = settings.thermostatResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.thermostatResumePlay3 == null) { resume = settings.thermostatResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "idle") {
        if (index == 1) { state.TalkPhrase = settings.thermostatTalkOnIdle1; state.speechDevice = thermostatSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.thermostatTalkOnIdle2; state.speechDevice = thermostatSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.thermostatTalkOnIdle3; state.speechDevice = thermostatSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "heating") {
        if (index == 1) { state.TalkPhrase = settings.thermostatTalkOnHeating1; state.speechDevice = thermostatSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.thermostatTalkOnHeating2; state.speechDevice = thermostatSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.thermostatTalkOnHeating3; state.speechDevice = thermostatSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "cooling") {
        if (index == 1) { state.TalkPhrase = settings.thermostatTalkOnCooling1; state.speechDevice = thermostatSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.thermostatTalkOnCooling2; state.speechDevice = thermostatSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.thermostatTalkOnCooling3; state.speechDevice = thermostatSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "fan only") {
        if (index == 1) { state.TalkPhrase = settings.thermostatTalkOnFan1; state.speechDevice = thermostatSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.thermostatTalkOnFan2; state.speechDevice = thermostatSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.thermostatTalkOnFan3; state.speechDevice = thermostatSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }

    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE THERMOSTAT

//BEGIN HANDLE ACCELERATION
def onAcceleration1Event(evt){
    processAccelerationEvent(1, evt)
}
def onAcceleration2Event(evt){
    processAccelerationEvent(2, evt)
}
def onAcceleration3Event(evt){
    processAccelerationEvent(3, evt)
}

def processAccelerationEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onAccelerationEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("acceleration",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("acceleration",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.accelerationResumePlay1 == null) { resume = settings.accelerationResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.accelerationResumePlay2 == null) { resume = settings.accelerationResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.accelerationResumePlay3 == null) { resume = settings.accelerationResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "active") {
        if (index == 1) { state.TalkPhrase = settings.accelerationTalkOnActive1; state.speechDevice = accelerationSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.accelerationTalkOnActive2; state.speechDevice = accelerationSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.accelerationTalkOnActive3; state.speechDevice = accelerationSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "inactive") {
        if (index == 1) { state.TalkPhrase = settings.accelerationTalkOnInactive1; state.speechDevice = accelerationSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.accelerationTalkOnInactive2; state.speechDevice = accelerationSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.accelerationTalkOnInactive3; state.speechDevice = accelerationSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE ACCELERATION

//BEGIN HANDLE WATER
def onWater1Event(evt){
    processWaterEvent(1, evt)
}
def onWater2Event(evt){
    processWaterEvent(2, evt)
}
def onWater3Event(evt){
    processWaterEvent(3, evt)
}

def processWaterEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onWaterEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("water",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("water",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.waterResumePlay1 == null) { resume = settings.waterResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.waterResumePlay2 == null) { resume = settings.waterResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.waterResumePlay3 == null) { resume = settings.waterResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "wet") {
        if (index == 1) { state.TalkPhrase = settings.waterTalkOnWet1; state.speechDevice = waterSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.waterTalkOnWet2; state.speechDevice = waterSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.waterTalkOnWet3; state.speechDevice = waterSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "dry") {
        if (index == 1) { state.TalkPhrase = settings.waterTalkOnDry1; state.speechDevice = waterSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.waterTalkOnDry2; state.speechDevice = waterSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.waterTalkOnDry3; state.speechDevice = waterSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE WATER

//BEGIN HANDLE SMOKE
def onSmoke1Event(evt){
    processSmokeEvent(1, evt)
}
def onSmoke2Event(evt){
    processSmokeEvent(2, evt)
}
def onSmoke3Event(evt){
    processSmokeEvent(3, evt)
}

def processSmokeEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onSmokeEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("smoke",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("smoke",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.smokeResumePlay1 == null) { resume = settings.smokeResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.smokeResumePlay2 == null) { resume = settings.smokeResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.smokeResumePlay3 == null) { resume = settings.smokeResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (evt.value == "detected") {
        if (index == 1) { state.TalkPhrase = settings.smokeTalkOnDetect1; state.speechDevice = smokeSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.smokeTalkOnDetect2; state.speechDevice = smokeSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.smokeTalkOnDetect3; state.speechDevice = smokeSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "clear") {
        if (index == 1) { state.TalkPhrase = settings.smokeTalkOnClear1; state.speechDevice = smokeSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.smokeTalkOnClear2; state.speechDevice = smokeSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.smokeTalkOnClear3; state.speechDevice = smokeSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    if (evt.value == "tested") {
        if (index == 1) { state.TalkPhrase = settings.smokeTalkOnTest1; state.speechDevice = smokeSpeechDevice1}
        if (index == 2) { state.TalkPhrase = settings.smokeTalkOnTest2; state.speechDevice = smokeSpeechDevice2}
        if (index == 3) { state.TalkPhrase = settings.smokeTalkOnTest3; state.speechDevice = smokeSpeechDevice3}
        if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    }
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE SMOKE

//BEGIN HANDLE BUTTON
def onButton1Event(evt){
    processButtonEvent(1, evt)
}
def onButton2Event(evt){
    processButtonEvent(2, evt)
}
def onButton3Event(evt){
    processButtonEvent(3, evt)
}

def processButtonEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onButtonEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("button",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("button",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.buttonResumePlay1 == null) { resume = settings.buttonResumePlay1 }
		}
		if (index == 2) {
			if (!settings?.buttonResumePlay2 == null) { resume = settings.buttonResumePlay2 }
		}
		if (index == 3) {
			if (!settings?.buttonResumePlay3 == null) { resume = settings.buttonResumePlay3 }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (index == 1 && evt.value == "pushed") { state.TalkPhrase = settings.buttonTalkOnPress1; state.speechDevice = buttonSpeechDevice1}
    if (index == 2 && evt.value == "pushed") { state.TalkPhrase = settings.buttonTalkOnPress2; state.speechDevice = buttonSpeechDevice2}
    if (index == 3 && evt.value == "pushed") { state.TalkPhrase = settings.buttonTalkOnPress3; state.speechDevice = buttonSpeechDevice3}
    if (index == 1 && evt.value == "held") { state.TalkPhrase = settings.buttonTalkOnHold1; state.speechDevice = buttonSpeechDevice1}
    if (index == 2 && evt.value == "held") { state.TalkPhrase = settings.buttonTalkOnHold2; state.speechDevice = buttonSpeechDevice2}
    if (index == 3 && evt.value == "held") { state.TalkPhrase = settings.buttonTalkOnHold3; state.speechDevice = buttonSpeechDevice3}
    if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE BUTTON

//BEGIN HANDLE SHM
def onSHMEvent(evt){
	if (evt.value == "away") {processSHMEvent(1, evt)}
    if (evt.value == "stay") {processSHMEvent(2, evt)}
    if (evt.value == "off") {processSHMEvent(3, evt)}
}

def processSHMEvent(index, evt){
	def resume = ""; resume = parent?.settings?.resumePlay; if (resume == "") { resume = true }
    LOGDEBUG("(onSHMEvent): ${evt.name}, ${index}, ${evt.value}")
    //Are we in an allowed time period?
    if (!(timeAllowed("SHM",index))) {
        LOGDEBUG("Remain silent in current time period")
        return
    }
    //Are we in a talking mode?
    if (!(modeAllowed("SHM",index))) { 
        LOGDEBUG("Remain silent while in mode ${location.mode}")
        return
    }
    state.TalkPhrase = null
    state.speechDevice = null
	if (parent.state.speechDeviceType == "capability.musicPlayer") {
		if (index == 1) {
			if (!settings?.SHMResumePlayAway == null) { resume = settings.SHMResumePlayAway }
		}
		if (index == 2) {
			if (!settings?.SHMResumePlayHome == null) { resume = settings.SHMResumePlayHome }
		}
		if (index == 3) {
			if (!settings?.SHMResumePlayDisarm == null) { resume = settings.SHMResumePlayDisarm }
		}
        if (resume == null) { resume = true }
	} else { resume = false }
    if (index == 1) {state.TalkPhrase = settings.SHMTalkOnAway; state.speechDevice = SHMSpeechDeviceAway}
    if (index == 2) {state.TalkPhrase = settings.SHMTalkOnHome; state.speechDevice = SHMSpeechDeviceHome}
    if (index == 3) {state.TalkPhrase = settings.SHMTalkOnDisarm; state.speechDevice = SHMSpeechDeviceDisarm}
    if (!(state?.TalkPhrase == null)) {parent.Talk(app.label,state.TalkPhrase, state.speechDevice, resume, evt)} else {LOGDEBUG("Not configured to speak for this event")}
    state.TalkPhrase = null
    state.speechDevice = null
}
//END HANDLE SHM
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
def LOGDEBUG(txt){
	if (parent?.settings?.debugmode) { parent.LOGDEBUG("[CHILD:${app.label}] ${txt}") }
    try {
    	if (parent?.settings?.debugmode) { log.debug("${app.label.replace(" ","").toUpperCase()}(${state.appversion}) || ${txt}")}
    } catch(ex) {
    	log.error("LOGDEBUG unable to output requested data!")
    }
}
def LOGTRACE(txt){
	parent.LOGTRACE("[CHILD:${app.label}] ${txt}")
    try {
    	log.trace("${app.label.replace(" ","").toUpperCase()}(${state.appversion}) || ${txt}")
    } catch(ex) {
    	log.error("LOGTRACE unable to output requested data!")
    }
}
def LOGERROR(txt){
	parent.LOGERROR("[CHILD:${app.label}] ${txt}")
    try {
    log.error("${app.label.replace(" ","").toUpperCase()}(${state.appversion}) || ERROR: ${txt}")
    } catch(ex) {
    	log.error("LOGERROR unable to output requested data!")
    }
}

def setAppVersion(){
    state.appversion = "Child-2.0.a4"
}
