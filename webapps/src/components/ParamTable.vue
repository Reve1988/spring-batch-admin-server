<template>
  <div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12">
      <h1>Job Parameters</h1>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>Job Execution ID</th>
            <th>Type</th>
            <th>Key</th>
            <th>Value(String)</th>
            <th>Value(Date)</th>
            <th>Value(Long)</th>
            <th>Value(Double)</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="param in paramList">
            <td>{{ jobExecutionId }}</td>
            <td>{{ param.typeCode }}</td>
            <td>{{ param.keyName }}</td>
            <td>{{ param.stringValue }}</td>
            <td>{{ param.dateValue }}</td>
            <td>{{ param.longValue }}</td>
            <td>{{ param.doubleValue }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'ParamTable',
    props: ['jobExecutionId'],
    data: function () {
      return {
        paramList: []
      }
    },
    watch: {
      jobExecutionId: function () {
        this.search();
      }
    },
    methods: {
      search: function () {
        this.paramList = [];

        if (!this.jobExecutionId || this.jobExecutionId <= 0) {
          return;
        }

        axios.get('/spring/batch/api/jobExecutionParams/' + this.jobExecutionId, {
          headers: {Pragma: 'no-cache'}
        }).then((response) => {
          this.paramList = response.data;
        }).catch((error) => {
          console.dir(error);
        });
      }
    }
  }
</script>

<style scoped>
  table tr td {
    word-break: break-all;
  }
</style>
