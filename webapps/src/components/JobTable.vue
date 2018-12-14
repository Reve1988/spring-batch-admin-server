<template>
  <div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12">
      <h1>Jobs</h1>
      <div class="table-responsive modal-table-responsive">
        <table class="table table-striped table-hover">
          <thead>
          <tr>
            <th>Job Execution ID</th>
            <th>Job Name</th>
            <th>Start</th>
            <th>End</th>
            <th>Duration</th>
            <th>Exit Code</th>
          </tr>
          </thead>
          <tbody>
          <tr @click="onClickJob(job)" v-for="job in jobList">
            <td>{{ job.jobExecutionId }}</td>
            <td>{{ job.jobName }}</td>
            <td>{{ job.startTime }}</td>
            <td>{{ job.endTime }}</td>
            <td>{{ duration(job.startTime, job.endTime) }}</td>
            <td>{{ job.exitCode }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <nav class="width-100">
        <ul class="pager">
          <li v-for="page in pageList">
            <a :class="[isCurrentPage(page) ? 'active' : '']" @click="search(page)" href="#">{{ page }}</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'JobTable',
    props: ['jobName'],
    data: function () {
      return {
        jobList: [],
        page: {
          index: 1,
          size: 10,
          total: 0
        }
      }
    },
    computed: {
      pageList: function () {
        let lastIndex = this.page.total / this.page.size;

        let startIndex = this.page.index - 2 > 0 ? this.page.index - 2 : 1;
        let endIndex = this.page.index + 2 < lastIndex ? this.page.index + 2 : lastIndex;

        let pageList = [];
        for (let index = startIndex; index <= endIndex; index++) {
          pageList.push(index);
        }

        return pageList;
      }
    },
    watch: {
      jobName: function () {
        this.search(1);
      }
    },
    methods: {
      isCurrentPage: function (index) {
        return index === this.page.index;
      },
      search: function (pageIndex) {
        if (!this.jobName || this.jobName === '') {
          return;
        }

        this.jobList = [];
        this.$emit('onClickJob', -1);

        axios.get('/spring/batch/api/jobExecutions/' + this.jobName, {
          headers: {Pragma: 'no-cache'},
          params: {
            start: (pageIndex - 1) * this.page.size,
            length: this.page.size
          }
        }).then((response) => {
          this.page.index = pageIndex;
          this.page.total = response.data.total;
          this.jobList = response.data.jobExecutionList;
        }).catch((error) => {
          console.dir(error);
        });
      },
      onClickJob: function (job) {
        this.$emit('onClickJob', job.jobExecutionId);
      },
      duration(start, end) {
        if (!start || !end) {
          return "";
        }

        let duration = this.$moment.duration(this.$moment(new Date(end)).diff(this.$moment(new Date(start))));
        const hours = Math.floor(duration.asHours());
        const minutes = Math.floor(duration.minutes());
        const seconds = Math.floor(duration.seconds());

        return hours + "h " + minutes + "m " + seconds + "s";
      }
    }
  }
</script>

<style scoped>
  table tr td {
    word-break: break-all;
  }

  .pager li {
    padding-left: 10px;
  }

  .pager li > a {
    display: inline-block;
    padding: 7px 13px;
    background-color: #3c3834;
    border: 1px solid #6db33f;
    border-radius: 0;
    color: #ffffff;
  }

  .pager li > a:hover {
    background-color: #6db33f;
  }

  .pager li > a.active {
    display: inline-block;
    padding: 7px 13px;
    background-color: #ffffff;
    border: 2px solid #6db33f;
    color: #3c3834;
  }

  .modal-table-responsive {
    overflow-x: hidden;
    overflow-y: auto;
    max-height: 500px;
  }
</style>
