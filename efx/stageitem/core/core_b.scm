	� G /efx/stageitem/core_bl.mot�       �U�n�F=[����\�	�MV��A^"�3�g��KRc��ْ50��%�\�sL����H��IkGH@��dU�Z_�q3iTh#�Y'#�:IQ�h����~�&yƸ�1��VD8�^�/��^K���	�3T�:;�^-�э���^�E�k�˯��ߞ�'W�wxx���i�pf�I;$��"#��0F/�'nq��ZW-'j��e�1�����8��c}�<���z��͛��.���Y*}��ƫ��;�E�R��,� ��Z�v�+烦/�6qBR�H�Y�!�y�@h[� i��,Ma������rt��Hó����ce�}N8�-C�@��Y����d����w�+�߶+K��N��[��c�����T~�$�
3W�h�@���4�/��V'��ޟuσ�N����坴�?H	��4�cZ�]
I�a���xT����C ��	�y.s)�1:���fi��$����"|�t������f3!4D����g��������������������;�cJ�]GX�IQ�G�SG��aX��5�+�CA-k���5[����*B�i�K=o!f+�����U��{�,��u�u��[��f˭�[aZK0n�"<X]Φ�*��������ٹ�,]�����r��Z���I�>`�Ђb���G������*
LU�!�D�!��&�[4z� ɦP�`#��~��v���I��1U̕���<&��6���hJC�re�<�%[KXPp�}ZG�M:���*�)��Xn��{�&IL#���z2�$!�$z��F��7:�-���F�|�<Ƥ׃PM�E�8#�-vA��lRۭ���q�_�2��NAdi�硎�c���ps�N��ԭ:�3)Cj��T$v�������8��ɍ�q$���3�p����7k(�
QDQ�]$��k�zbV{�ê0+���<��m�rW�Zb���5�Z[?V�߱Wc�f�5(�10����dR�.񾙐�����Y�P�p])x���ؚ�	�:�OT
   u  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar7.agi���� mix2��� /efx/mix/bar4.agi���� Lkotori/data/KGLGenerationData; ripD /efx/ripple/ripple05_blu.ptc�            ?�  ?�  ?�               baseD "efx/stageitem/core/core_b_base.scm�            ?�  ?�  ?�                flashD */efx/stageitem/trap/item_box/ib_flash1.scm�        �333?�  ?�  ?�               start appear 	damagehit destroy revival exp setShadowState
 d y 3 getPosition ()Lkotori/geom/Position;
 f { | l pos (Lkotori/geom/Vector3D;)V
 ~  � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � setJudgmentKind
 f � 3 Lga2/stage/ItemModel; � JUDGE_DAMAGE_HIT	 � �  enableCollision (Z)V
 f � � 
setVisible
 f � � A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D efx/electric/rnd_thunder.scm�        �333?�  ?�  ?�               	main_core anime (F)V
 d � �A   
 H 0 3 setColor (FFFF)V
 d � �=��� 	fadeColor (FFFFI)V
 d � �>��� (FF)V
 d � �AP  =L��A�   
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �Ap  A�   (IFFFFFFF)V
 d � �C�  @�  D *efx/stageitem/core/core_efx_center_mix.scm�        ��?�  ?�  ?�              A blue removeMixture
 d � 1 	playSound
 d � 3 vibe
 d � �=�\)CH   getParam &(Ljava/lang/String;)Ljava/lang/Object;
 f � � HP Ljava/lang/Number; � intValue
 � � 2 addHP
 f � 3 rndf (F)F
 H � � hpb (FFF)V
 ~ � �C�   scale
 ~ � � brown?L��<uA�  >�=q<���A�  D /efx/explode/exp_sphere1.scm�        �333?333?333?333             quake
 d � 3 f h g j g m l o l q l - 1  i� k�n�p�r��  s 1  +*� zW�*� }� �W*�� �W*� �� �W*� �W*� W�  t 1  �*� �W*��� �W*�� �W* � �W*���� �W*���,� �W*2� �W*��� �W*2� �W*��� �W*� � �W*�� �W*2� �W*��� �W* �� �W*�� �W*d� �W*d� �W*d� �W*�� �W*�� �W*,� �W*��� �W*� �W*d� �W*� �W�  u 1  *� �W*� �W*��,� �W�  v 1  *� �W*� �W*� W�  w 1  *� W*�� �L*+� ö �W�  x 1 J*� � �W*�� �W�̸ �̸ �̸ ȶ �W��� �b� �W*�϶ �W*� �W*�� �W*��� �W*2� �W�̸ �̸ �̸ ȶ �W��� �b� �W*�϶ �W*2� �W�̸ �̸ �̸ ȶ �W��� �b� �W*�϶ �W*2� �W*� � �W*�� �W*��\�� �W*2� �W*�Ӷ �W*d� �W*��\�� �W* �� �W*��� �W*!� �W*ֶ �W*� �W*d� �W*� �W*�� �W*� �W� ��Tݼ � =�<�x[�?f&�?Me�?[X� � coreType�?�  � HP�A�  �	 	collision�
1    =��;���?333?333?��