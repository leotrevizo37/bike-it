# Contributing to BikeIt

Thank you for your interest in contributing to this project! We value contributions from the community and want to make the process as smooth as possible.

## Getting Started

> [!CAUTION]
> **Do NOT force-push to your PR branch** unless absolutely necessary. A force-push breaks the PR review and will cause significant delays to the review process. A clean branch history is not important for merging the PR: this repository uses squash-merge, so each PR is collapsed into a single commit using the PR's title.

### Project Overview

Our codebase is organized as follows:

- `.github/`: Contains GitHub files like issue templates and workflows

## Development Workflow

### Commit Messages

Please follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification for your commit messages. This helps with:

- Automatic changelog generation
- Understanding the history of changes
- Semantic versioning

Format:
```
<type>(<scope>): <description>
```

- **`type`**: The type of change (e.g., `feat`, `fix`, `docs`, `chore`, etc.)
- **`scope`**: A short, lowercase description of the section of the codebase affected (e.g., `tmpDir-to-tmpdir`, `esm-migration`)
- **`description`**: A concise summary of the change

Examples:
- `feat(bike-history): add new feature to bike tracking`
- `fix(data): correct data transformation`

## Pull Request Process

When submitting a pull request:
1. Ensure your changes are well-documented
2. Run all your tests (`if your contrubution requires it`)
3. Follow the project's coding standards
4. Use the [conventional commit](https://www.conventionalcommits.org/en/v1.0.0/) format in your PR title and description
5. Link to any related issues, using [GitHub keywords](https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/using-keywords-in-issues-and-pull-requests) where applicable.

### Acceptance Criteria

For a pull request to be merged, it must:
- Receive approval from at least 2 reviewers with write access
- Receive no objections from reviewers with write access
- Pass all tests
- Be open for at least 48 hours to allow for review and discussion
  - except hotfixes and trivial corrections (like typos)

### Developer's Certificate of Origin 1.1

```
By contributing to this project, I certify that:

- (a) The contribution was created in whole or in part by me and I have the right to
  submit it under the open source license indicated in the file; or
- (b) The contribution is based upon previous work that, to the best of my knowledge,
  is covered under the Noncommercial license and I have the right under that
  license to submit that work with modifications, whether created in whole or in part
  by me, under the same Noncommercial license (unless I am permitted to submit under a
  different license), as indicated in the file; or
- (c) The contribution was provided directly to me by some other person who certified
  (a), (b) or (c) and I have not modified it.
- (d) I understand and agree that this project and the contribution are public and that
  a record of the contribution (including all personal information I submit with it,
  including my sign-off) is maintained indefinitely and may be redistributed consistent
  with this project or the Noncommercial license(s) involved.
```
