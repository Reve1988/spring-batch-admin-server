<template>
  <div class="container xd-container">
    <searchbox @onKeydownEnter="getJobName"></searchbox>
    <job-table :job-name="jobName" @onClickJob="getJobExecutionId"></job-table>
    <param-table :job-execution-id="jobExecutionId"></param-table>
    <step-table :job-execution-id="jobExecutionId"></step-table>
  </div>
</template>

<script>
  import Searchbox from "./Searchbox";
  import JobTable from "./JobTable";
  import StepTable from "./StepTable";
  import ParamTable from "./ParamTable";

  export default {
    name: 'Contents',
    data: function () {
      return {
        jobName: '',
        jobExecutionId: -1,
      }
    },
    components: {
      ParamTable,
      JobTable,
      StepTable,
      Searchbox
    },
    mounted: function () {
      const urlParams = new URLSearchParams(window.location.search);
      this.jobName = urlParams.get('jobName');
    },
    methods: {
      getJobName: function (jobName) {
        this.jobName = jobName;
      },
      getJobExecutionId: function (jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
      }
    }
  }
</script>
