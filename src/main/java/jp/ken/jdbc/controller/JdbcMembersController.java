package jp.ken.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ken.jdbc.dao.MembersDao;
import jp.ken.jdbc.entity.Members;
import jp.ken.jdbc.group.GroupOrder;
import jp.ken.jdbc.model.MembersModel;
import jp.ken.jdbc.model.MembersSearchModel;

@Controller
public class JdbcMembersController {
		@Autowired
		private MembersDao membersDao;

		@RequestMapping(value ="/search", method = RequestMethod.GET)
		public String toSearch(Model model) {
			model.addAttribute("membersSearchModel", new MembersSearchModel());
			model.addAttribute("headline","会員検索");
			return "membersSearch";
		}

		@RequestMapping(value ="/search", method = RequestMethod.POST)
		public String searchMembers(@ModelAttribute MembersSearchModel membersSearchModel, Model model) {
			boolean idIsEmpty = membersSearchModel.getId().isEmpty();
			boolean nameIsEmpty = membersSearchModel.getName().isEmpty();

			if(idIsEmpty && nameIsEmpty) {
				List<Members> membersList = membersDao.getList();
				model.addAttribute("membersList",membersList	);
			}else if(!idIsEmpty && nameIsEmpty) {
				try {
					Integer id = new Integer(membersSearchModel.getId());
					Members members = membersDao.getMembersById(id);

					if(members == null) {
						model.addAttribute("message","該当データがありません");
					} else {
						List<Members> membersList = new ArrayList<Members>();
						membersList.add(members);
						model.addAttribute("membersList", membersList);
					}
				}catch(NumberFormatException e) {
					model.addAttribute("message","IDが不正です");
				}
			}else if (idIsEmpty && !nameIsEmpty) {
				List<Members> membersList	 = membersDao.getListByName(membersSearchModel.getName());

				if(membersList.isEmpty()) {
					model.addAttribute("message","該当データはありません");
				}else {
					model.addAttribute("membersList",membersList);
				}
			}else {
				model.addAttribute("message", "IDまたは氏名のいずれかを入力してください");
			}

			model.addAttribute("headline","会員検索");
			return "membersSearch";
		}

		@RequestMapping(value = "/registration",method = RequestMethod.GET)
		public String toRegistration(Model model) {
			model.addAttribute("membersModel",new MembersModel());
			model.addAttribute("headline","会員登録");
			return "membersRegistration";
			}

		@RequestMapping(value = "/registration",method = RequestMethod.POST)
		public String registMembers(Model model,@Validated(GroupOrder.class) @ModelAttribute MembersModel membersModel,BindingResult result) {
			if(result.hasErrors()) {
				model.addAttribute("headline","会員登録");
				return "membersRegistration";
			}

			Members members = new Members();
			members.setName(membersModel.getName());
			members.setEmail(membersModel.getEmail());
			members.setPhoneNumber(membersModel.getPhoneNumber());
			members.setBirthday(Members.parseDate(membersModel.getBirthday()));

			int numberOfRow = membersDao.insert(members);
			if(numberOfRow == 0) {
				model.addAttribute("message","登録に失敗しました。");
				model.addAttribute("headline","会員登録");
				return "membersRegistration";
			}

			return "redirect:/complete";
		}

		@RequestMapping(value = "/complete",method = RequestMethod.GET)
		public String toComplete(Model model) {
			model.addAttribute("headline","会員登録完了");
			return "membersRegistrationComplete";
		}

		// ...（省略）...
		@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
		public String deleteMember(@RequestParam Integer id, Model model) {
		    Members members = membersDao.getMembersById(id);
		    if (members != null) {
		        int numberOfRow = membersDao.delete(members); // 削除メソッドの実行（削除処理はDAOクラスに実装する必要があります）
		        if (numberOfRow > 0) {
		            // 削除成功時の処理（メッセージなど）
		        } else {
		            model.addAttribute("message", "削除に失敗しました。");
		        }
		    } else {
		        model.addAttribute("message", "該当の会員が存在しません。");
		    }
		    // 再度検索画面にリダイレクトまたはフォワードするなど
		    return "redirect:/search";
		}
		// ...（省略）...

		// JdbcMembersController.java

		// ...（省略）...

}
