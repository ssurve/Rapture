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
    public interface RepoApi {
     /**
     * Retrieve the content given a documentURI
     * @deprecated The Repo API is being eliminated
     */
     ContentEnvelope getContent(CallingContext context, string raptureURI);

     /**
     * Store the content supplied at the document URI given. Existing content will be overwritten at this URI, 
         but the previous version may be stored if the underlying repository supports it
     * @deprecated The Repo API is being eliminated
     */
     void putContent(CallingContext context, string raptureURI, Object content, string comment);

     /**
     * Remove a document from the system.
     * @deprecated The Repo API is being eliminated
     */
     void deleteContent(CallingContext context, string raptureURI, string comment);

	}
}
