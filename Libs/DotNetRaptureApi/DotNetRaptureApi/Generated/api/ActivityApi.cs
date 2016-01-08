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
    public interface ActivityApi {
     /**
     * This method creates and starts recording a new activity. It returns a unique id that can be used to update the status of the activity.
     * 
     */
     string createActivity(CallingContext context, string description, string message, long progress, long max);

     /**
     * This method updates the status of an activity.
       The return value is false if the activity was already marked as finished or aborted.  If the value is false, this function will not take effect.
     * 
     */
     bool updateActivity(CallingContext context, string activityId, string message, long progress, long max);

     /**
     * This method marks an activity as finished.
       The return value is false if the activity was already marked as finished or aborted.  If the value is false, this function will not take effect.
     * 
     */
     bool finishActivity(CallingContext context, string activityId, string message);

     /**
     * This method is used to request that an activity abort. This will indicate to callers of updateActivity that the request is aborted, via the return value
       of calls that write to this activity, such as updateActivity or recordActivity.
       The return value is false if the activity was already marked as finished or aborted.  If the value is false, this function will not take effect.
     * 
     */
     bool requestAbortActivity(CallingContext context, string activityId, string message);

     /**
     * Retrieve activities updated after a given timestamp
       - nextBatchId: the id for the batch, if this is not the first request. Empty string to indicate the first request
       - batchSize: the maximum number of items you want to see in this batch. Maximum is 10000 -- if the number passed in is > 10k, it gets set to 10k.
       - lastSeen: an epoch timestamp in milliseconds. only activities that were last updated after this time will be returned
       
     * 
     */
     ActivityQueryResponse queryByExpiryTime(CallingContext context, string nextBatchId, long batchSize, long lastSeen);

     /**
     * Get an activity by id
     * 
     */
     Activity getById(CallingContext context, string activityId);

	}
}
