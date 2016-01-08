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
 * This is an autogenerated file. You should not edit this file as any changes
 * will be overwritten.
 */

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DotNetRaptureAPI.Common.FixedTypes;

namespace DotNetRaptureAPI
{

public interface ScriptPluginApi {
     /**
     * 
     * 
     */
     List<PluginConfig> getInstalledPlugins();

     /**
     * 
     * 
     */
     PluginManifest getPluginManifest(string manifestUri);

     /**
     * 
     * 
     */
     void recordPlugin(PluginConfig plugin);

     /**
     * 
     * 
     */
     void installPlugin(PluginManifest manifest, Dictionary<string, PluginTransportItem> payload);

     /**
     * 
     * 
     */
     void installPluginItem(string pluginName, PluginTransportItem item);

     /**
     * 
     * 
     */
     void uninstallPlugin(string name);

     /**
     * 
     * 
     */
     void uninstallPluginItem(PluginTransportItem item);

     /**
     * 
     * 
     */
     void deletePluginManifest(string manifestUri);

     /**
     * 
     * 
     */
     PluginTransportItem getPluginItem(string uri);

     /**
     * 
     * 
     */
     Dictionary<string, string> verifyPlugin(string plugin);

     /**
     * 
     * 
     */
     void createManifest(string pluginName);

     /**
     * 
     * 
     */
     void addManifestItem(string pluginName, string uri);

     /**
     * 
     * 
     */
     void addManifestDataFolder(string pluginName, string uri);

     /**
     * 
     * 
     */
     void removeManifestDataFolder(string pluginName, string uri);

     /**
     * 
     * 
     */
     void setManifestVersion(string pluginName, string version);

     /**
     * 
     * 
     */
     void removeItemFromManifest(string pluginName, string uri);

     /**
     * 
     * 
     */
     string exportPlugin(string pluginName, string path);

}
}
