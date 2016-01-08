/**
 * Copyright (C) 2011-2013 Incapture Technologies LLC
 * 
 * This is an autogenerated license statement. When copyright notices appear below
 * this one that copyright supercedes this statement.
 *
 * Unless required by applicable law or agreed to in writing, software is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * Unless explicit permission obtained in writing this software cannot be distributed.
 */

/**
 * This file is autogenerated and any changes will be overwritten.
 */

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DotNetRaptureAPI;
using DotNetRaptureAPI.Common;
using DotNetRaptureAPI.Common.FixedTypes;
using DotNetRaptureAPI.Utils;

namespace DotNetRaptureAPI.script
{

public class HttpScriptApi : BaseHttpApi , ScriptApi, ScriptScriptApi {
	public HttpScriptApi(HttpLoginApi login) : base(login, "script") {
	
	}
		
	   
	    public RaptureScript createScript(CallingContext context,  string scriptURI,  RaptureScriptLanguage language,  RaptureScriptPurpose purpose,  string script)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            CreateScriptPayload requestObj = new CreateScriptPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.language = language;
	        requestObj.purpose = purpose;
	        requestObj.script = script;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("CREATESCRIPT", RaptureSerializer.SerializeJson<CreateScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureScript>(resp.response.content);
	            }
	             throw new Exception("Error in createScript, no response returned");
	        }
	   
	    public void createScriptLink(CallingContext context,  string fromScriptURI,  string toScriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            CreateScriptLinkPayload requestObj = new CreateScriptLinkPayload();
	        requestObj.fromScriptURI = fromScriptURI;
	        requestObj.toScriptURI = toScriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("CREATESCRIPTLINK", RaptureSerializer.SerializeJson<CreateScriptLinkPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in createScriptLink, no response returned");
	        }
	   
	    public void removeScriptLink(CallingContext context,  string fromScriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            RemoveScriptLinkPayload requestObj = new RemoveScriptLinkPayload();
	        requestObj.fromScriptURI = fromScriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("REMOVESCRIPTLINK", RaptureSerializer.SerializeJson<RemoveScriptLinkPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in removeScriptLink, no response returned");
	        }
	   
	    public RaptureScript setScriptParameters(CallingContext context,  string scriptURI,  List<RaptureParameter> parameters)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            SetScriptParametersPayload requestObj = new SetScriptParametersPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.parameters = parameters;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("SETSCRIPTPARAMETERS", RaptureSerializer.SerializeJson<SetScriptParametersPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureScript>(resp.response.content);
	            }
	             throw new Exception("Error in setScriptParameters, no response returned");
	        }
	   
	    public bool doesScriptExist(CallingContext context,  string scriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            DoesScriptExistPayload requestObj = new DoesScriptExistPayload();
	        requestObj.scriptURI = scriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("DOESSCRIPTEXIST", RaptureSerializer.SerializeJson<DoesScriptExistPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<bool>(resp.response.content);
	            }
	             throw new Exception("Error in doesScriptExist, no response returned");
	        }
	   
	    public void deleteScript(CallingContext context,  string scriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            DeleteScriptPayload requestObj = new DeleteScriptPayload();
	        requestObj.scriptURI = scriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("DELETESCRIPT", RaptureSerializer.SerializeJson<DeleteScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in deleteScript, no response returned");
	        }
	   
	    public List<string> getScriptNames(CallingContext context,  string scriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            GetScriptNamesPayload requestObj = new GetScriptNamesPayload();
	        requestObj.scriptURI = scriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("GETSCRIPTNAMES", RaptureSerializer.SerializeJson<GetScriptNamesPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<List<string>>(resp.response.content);
	            }
	             throw new Exception("Error in getScriptNames, no response returned");
	        }
	   
	    public RaptureScript getScript(CallingContext context,  string scriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            GetScriptPayload requestObj = new GetScriptPayload();
	        requestObj.scriptURI = scriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("GETSCRIPT", RaptureSerializer.SerializeJson<GetScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureScript>(resp.response.content);
	            }
	             throw new Exception("Error in getScript, no response returned");
	        }
	   
	    public RaptureScript putScript(CallingContext context,  string scriptURI,  RaptureScript script)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            PutScriptPayload requestObj = new PutScriptPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.script = script;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("PUTSCRIPT", RaptureSerializer.SerializeJson<PutScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureScript>(resp.response.content);
	            }
	             throw new Exception("Error in putScript, no response returned");
	        }
	   
	    public RaptureScript putRawScript(CallingContext context,  string scriptURI,  string content,  string language,  string purpose,  List<string> param_types,  List<string> param_names)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            PutRawScriptPayload requestObj = new PutRawScriptPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.content = content;
	        requestObj.language = language;
	        requestObj.purpose = purpose;
	        requestObj.param_types = param_types;
	        requestObj.param_names = param_names;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("PUTRAWSCRIPT", RaptureSerializer.SerializeJson<PutRawScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureScript>(resp.response.content);
	            }
	             throw new Exception("Error in putRawScript, no response returned");
	        }
	   
	    public string runScript(CallingContext context,  string scriptURI,  Dictionary<string, string> parameters)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            RunScriptPayload requestObj = new RunScriptPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.parameters = parameters;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("RUNSCRIPT", RaptureSerializer.SerializeJson<RunScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<string>(resp.response.content);
	            }
	             throw new Exception("Error in runScript, no response returned");
	        }
	   
	    public ScriptResult runScriptExtended(CallingContext context,  string scriptURI,  Dictionary<string, string> parameters)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            RunScriptExtendedPayload requestObj = new RunScriptExtendedPayload();
	        requestObj.scriptURI = scriptURI;
	        requestObj.parameters = parameters;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("RUNSCRIPTEXTENDED", RaptureSerializer.SerializeJson<RunScriptExtendedPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<ScriptResult>(resp.response.content);
	            }
	             throw new Exception("Error in runScriptExtended, no response returned");
	        }
	   
	    public string checkScript(CallingContext context,  string scriptURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            CheckScriptPayload requestObj = new CheckScriptPayload();
	        requestObj.scriptURI = scriptURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("CHECKSCRIPT", RaptureSerializer.SerializeJson<CheckScriptPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<string>(resp.response.content);
	            }
	             throw new Exception("Error in checkScript, no response returned");
	        }
	   
	    public string createREPLSession(CallingContext context)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            CreateREPLSessionPayload requestObj = new CreateREPLSessionPayload();
	            string responseObjectJson;
	            responseObjectJson = makeRequest("CREATEREPLSESSION", RaptureSerializer.SerializeJson<CreateREPLSessionPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<string>(resp.response.content);
	            }
	             throw new Exception("Error in createREPLSession, no response returned");
	        }
	   
	    public void destroyREPLSession(CallingContext context,  string sessionId)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            DestroyREPLSessionPayload requestObj = new DestroyREPLSessionPayload();
	        requestObj.sessionId = sessionId;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("DESTROYREPLSESSION", RaptureSerializer.SerializeJson<DestroyREPLSessionPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in destroyREPLSession, no response returned");
	        }
	   
	    public string evaluateREPL(CallingContext context,  string sessionId,  string line)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            EvaluateREPLPayload requestObj = new EvaluateREPLPayload();
	        requestObj.sessionId = sessionId;
	        requestObj.line = line;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("EVALUATEREPL", RaptureSerializer.SerializeJson<EvaluateREPLPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<string>(resp.response.content);
	            }
	             throw new Exception("Error in evaluateREPL, no response returned");
	        }
	   
	    public void archiveOldREPLSessions(CallingContext context,  long ageInMinutes)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            ArchiveOldREPLSessionsPayload requestObj = new ArchiveOldREPLSessionsPayload();
	        requestObj.ageInMinutes = ageInMinutes;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("ARCHIVEOLDREPLSESSIONS", RaptureSerializer.SerializeJson<ArchiveOldREPLSessionsPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in archiveOldREPLSessions, no response returned");
	        }
	   
	    public RaptureSnippet createSnippet(CallingContext context,  string snippetURI,  string snippet)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            CreateSnippetPayload requestObj = new CreateSnippetPayload();
	        requestObj.snippetURI = snippetURI;
	        requestObj.snippet = snippet;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("CREATESNIPPET", RaptureSerializer.SerializeJson<CreateSnippetPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureSnippet>(resp.response.content);
	            }
	             throw new Exception("Error in createSnippet, no response returned");
	        }
	   
	    public List<RaptureFolderInfo> getSnippetChildren(CallingContext context,  string prefix)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            GetSnippetChildrenPayload requestObj = new GetSnippetChildrenPayload();
	        requestObj.prefix = prefix;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("GETSNIPPETCHILDREN", RaptureSerializer.SerializeJson<GetSnippetChildrenPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<List<RaptureFolderInfo>>(resp.response.content);
	            }
	             throw new Exception("Error in getSnippetChildren, no response returned");
	        }
	   
	    public void deleteSnippet(CallingContext context,  string snippetURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            DeleteSnippetPayload requestObj = new DeleteSnippetPayload();
	        requestObj.snippetURI = snippetURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("DELETESNIPPET", RaptureSerializer.SerializeJson<DeleteSnippetPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<void>(resp.response.content);
	            }
	             throw new Exception("Error in deleteSnippet, no response returned");
	        }
	   
	    public RaptureSnippet getSnippet(CallingContext context,  string snippetURI)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            GetSnippetPayload requestObj = new GetSnippetPayload();
	        requestObj.snippetURI = snippetURI;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("GETSNIPPET", RaptureSerializer.SerializeJson<GetSnippetPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<RaptureSnippet>(resp.response.content);
	            }
	             throw new Exception("Error in getSnippet, no response returned");
	        }
	   
	    public Dictionary<string, RaptureFolderInfo> listScriptsByUriPrefix(CallingContext context,  string uriPrefix,  int depth)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            ListScriptsByUriPrefixPayload requestObj = new ListScriptsByUriPrefixPayload();
	        requestObj.uriPrefix = uriPrefix;
	        requestObj.depth = depth;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("LISTSCRIPTSBYURIPREFIX", RaptureSerializer.SerializeJson<ListScriptsByUriPrefixPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<Dictionary<string, RaptureFolderInfo>>(resp.response.content);
	            }
	             throw new Exception("Error in listScriptsByUriPrefix, no response returned");
	        }
	   
	    public List<string> deleteScriptsByUriPrefix(CallingContext context,  string uriPrefix)
	        {
	            System.Web.Script.Serialization.JavaScriptSerializer oSerializer = 
	         new System.Web.Script.Serialization.JavaScriptSerializer();
	            oSerializer.RegisterConverters(new System.Web.Script.Serialization.JavaScriptConverter[] { new JsonContentConverter() });
	            DeleteScriptsByUriPrefixPayload requestObj = new DeleteScriptsByUriPrefixPayload();
	        requestObj.uriPrefix = uriPrefix;
	            string responseObjectJson;
	            responseObjectJson = makeRequest("DELETESCRIPTSBYURIPREFIX", RaptureSerializer.SerializeJson<DeleteScriptsByUriPrefixPayload>(requestObj));
	            if (responseObjectJson != null && responseObjectJson.Length > 0) {
	              GeneralResponse resp = GeneralResponseConverter.retrieveFromString(responseObjectJson);
	              return RaptureSerializer.DeserializeJson<List<string>>(resp.response.content);
	            }
	             throw new Exception("Error in deleteScriptsByUriPrefix, no response returned");
	        }
	
	       public RaptureScript createScript(string scriptURI, RaptureScriptLanguage language, RaptureScriptPurpose purpose, string script) {
	            return createScript(null,scriptURI,language,purpose,script);
	       }

	       public void createScriptLink(string fromScriptURI, string toScriptURI) {
	            return createScriptLink(null,fromScriptURI,toScriptURI);
	       }

	       public void removeScriptLink(string fromScriptURI) {
	            return removeScriptLink(null,fromScriptURI);
	       }

	       public RaptureScript setScriptParameters(string scriptURI, List<RaptureParameter> parameters) {
	            return setScriptParameters(null,scriptURI,parameters);
	       }

	       public bool doesScriptExist(string scriptURI) {
	            return doesScriptExist(null,scriptURI);
	       }

	       public void deleteScript(string scriptURI) {
	            return deleteScript(null,scriptURI);
	       }

	       public List<string> getScriptNames(string scriptURI) {
	            return getScriptNames(null,scriptURI);
	       }

	       public RaptureScript getScript(string scriptURI) {
	            return getScript(null,scriptURI);
	       }

	       public RaptureScript putScript(string scriptURI, RaptureScript script) {
	            return putScript(null,scriptURI,script);
	       }

	       public RaptureScript putRawScript(string scriptURI, string content, string language, string purpose, List<string> param_types, List<string> param_names) {
	            return putRawScript(null,scriptURI,content,language,purpose,param_types,param_names);
	       }

	       public string runScript(string scriptURI, Dictionary<string, string> parameters) {
	            return runScript(null,scriptURI,parameters);
	       }

	       public ScriptResult runScriptExtended(string scriptURI, Dictionary<string, string> parameters) {
	            return runScriptExtended(null,scriptURI,parameters);
	       }

	       public string checkScript(string scriptURI) {
	            return checkScript(null,scriptURI);
	       }

	       public string createREPLSession() {
	            return createREPLSession(null);
	       }

	       public void destroyREPLSession(string sessionId) {
	            return destroyREPLSession(null,sessionId);
	       }

	       public string evaluateREPL(string sessionId, string line) {
	            return evaluateREPL(null,sessionId,line);
	       }

	       public void archiveOldREPLSessions(long ageInMinutes) {
	            return archiveOldREPLSessions(null,ageInMinutes);
	       }

	       public RaptureSnippet createSnippet(string snippetURI, string snippet) {
	            return createSnippet(null,snippetURI,snippet);
	       }

	       public List<RaptureFolderInfo> getSnippetChildren(string prefix) {
	            return getSnippetChildren(null,prefix);
	       }

	       public void deleteSnippet(string snippetURI) {
	            return deleteSnippet(null,snippetURI);
	       }

	       public RaptureSnippet getSnippet(string snippetURI) {
	            return getSnippet(null,snippetURI);
	       }

	       public Dictionary<string, RaptureFolderInfo> listScriptsByUriPrefix(string uriPrefix, int depth) {
	            return listScriptsByUriPrefix(null,uriPrefix,depth);
	       }

	       public List<string> deleteScriptsByUriPrefix(string uriPrefix) {
	            return deleteScriptsByUriPrefix(null,uriPrefix);
	       }

}
}

