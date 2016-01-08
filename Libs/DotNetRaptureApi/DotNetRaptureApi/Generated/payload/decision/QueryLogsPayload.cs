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
 * This file is autogenerated and any changes made to it will be overwritten
 */

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using DotNetRaptureAPI.Common.FixedTypes;

namespace DotNetRaptureAPI.decision
{
    [DataContract]
    public class QueryLogsPayload
    {
		    [DataMember (Name="workOrderURI")]
			public string workOrderURI { get; set; }



		    [DataMember (Name="startTime")]
			public long startTime { get; set; }



		    [DataMember (Name="endTime")]
			public long endTime { get; set; }



		    [DataMember (Name="keepAlive")]
			public long keepAlive { get; set; }



		    [DataMember (Name="bufferSize")]
			public long bufferSize { get; set; }



		    [DataMember (Name="nextBatchId")]
			public string nextBatchId { get; set; }



		    [DataMember (Name="stepName")]
			public string stepName { get; set; }



		    [DataMember (Name="stepStartTime")]
			public string stepStartTime { get; set; }



	}
}
